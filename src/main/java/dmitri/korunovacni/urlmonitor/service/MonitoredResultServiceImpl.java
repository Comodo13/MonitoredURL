package dmitri.korunovacni.urlmonitor.service;

import dmitri.korunovacni.urlmonitor.exception.RequestFormatException;
import dmitri.korunovacni.urlmonitor.exception.ResourceNotFoundException;
import dmitri.korunovacni.urlmonitor.model.MonitoredEndpoint;
import dmitri.korunovacni.urlmonitor.model.MonitoredResult;
import dmitri.korunovacni.urlmonitor.model.dto.MonitoredResultDto;
import dmitri.korunovacni.urlmonitor.model.transformer.ResultTransformer;
import dmitri.korunovacni.urlmonitor.repo.EndpointRepository;
import dmitri.korunovacni.urlmonitor.repo.ResultRepository;
import dmitri.korunovacni.urlmonitor.scheduling.CheckUrlTask;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MonitoredResultServiceImpl implements MonitoredResultService {

    private final ResultRepository resultRepository;
    private final EndpointRepository endpointRepository;

    public void startScheduleChecking(MonitoredEndpoint endpoint) {
        Timer timer = new Timer();
        CheckUrlTask check = new CheckUrlTask(new MonitoredResultServiceImpl(
                resultRepository, endpointRepository), endpoint);
        timer.schedule(check, 0, endpoint.getMonitoredIntervalInSeconds() * 1000);
    }

    @Override
    public ResponseEntity<List<MonitoredResultDto>> getLastTenResultsByEndpointId(Long id)
            throws RequestFormatException, ResourceNotFoundException {
        if (id == null || id == 0L) {
            throw new RequestFormatException("Endpoint Id can't be zero or null");
        }
        List<MonitoredResult> results;
        Optional<MonitoredEndpoint> endpointOpt = endpointRepository.findById(id);
        if (endpointOpt.isPresent()) {
            results = endpointOpt.get().getResults();
        } else throw new ResourceNotFoundException("Endpoint with ID: " + id + " was not found");

        results.sort(Comparator.comparing(MonitoredResult::getDateOfCheck).reversed());
        if (results.size() >= 10) {
            results.subList(0, 10);
        } else if (results.size() == 0) {
            results = new ArrayList<>();
        } else results.subList(0, results.size());

        List<MonitoredResultDto> resultsDtos = results
                .stream()
                .map(ResultTransformer::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(resultsDtos, HttpStatus.OK);

    }

    @Override
    public void createResult(String payload, int statusCode, MonitoredEndpoint endpoint) {
        LocalDateTime dateOfCheck = LocalDateTime.now();
        MonitoredResult result = new MonitoredResult();
        result.setReturnedPayload(payload.substring(0, 9000)); //shortened payload to 9000 elements
        result.setStatusCode(statusCode);
        result.setDateOfCheck(dateOfCheck);
        result.setEndpointId(endpoint.getId());

        endpoint.setDateOfLastCheck(dateOfCheck);

        endpointRepository.save(endpoint);
        resultRepository.save(result);
    }
}

