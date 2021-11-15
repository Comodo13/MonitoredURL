package dmitri.korunovacni.Applifting.service;

import dmitri.korunovacni.Applifting.model.MonitoredResult;
import dmitri.korunovacni.Applifting.repo.EndpointRepository;
import dmitri.korunovacni.Applifting.scheduling.CheckUrlTask;
import dmitri.korunovacni.Applifting.model.MonitoredEndpoint;
import dmitri.korunovacni.Applifting.repo.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class MonitoredResultServiceImpl implements MonitoredResultService {

    private final ResultRepository resultRepository;
    private final EndpointRepository endpointRepository;

    @Autowired
    public MonitoredResultServiceImpl(ResultRepository resultRepository, EndpointRepository endpointRepository) {
        this.resultRepository = resultRepository;
        this.endpointRepository = endpointRepository;
    }

    public void startScheduleChecking(MonitoredEndpoint endpoint) {
        Timer timer = new Timer();
        CheckUrlTask check = new CheckUrlTask(new MonitoredResultServiceImpl(
                resultRepository, endpointRepository), endpoint);
        timer.schedule(check, 0, endpoint.getMonitoredIntervalInSeconds()*1000);
    }

@Override
public List<MonitoredResult> getLastTenResultsByEndpointId(Long id) {
    MonitoredEndpoint point = endpointRepository.getById(id);
    List<MonitoredResult> results = point.getResults();

    results.sort(Comparator.comparing(MonitoredResult::getDateOfCheck).reversed());
    if (results.size()>=10) {
        return results.subList(0,10);
    }
    else if (results.size()==0) {
        return new ArrayList<>();
    }
    else
        return  results.subList(0,results.size());
    }

    @Override
    public void createResult(String payload, int statusCode, MonitoredEndpoint endpoint) {
        LocalDateTime dateOfCheck = LocalDateTime.now();
        MonitoredResult result = new MonitoredResult();
        result.setReturnedPayload(payload.substring(0,9000)); //shortened payload to 9000 elements
        result.setStatusCode(statusCode);
        result.setDateOfCheck(dateOfCheck);
        result.setEndpointId(endpoint.getId());

        endpoint.setDateOfLastCheck(dateOfCheck);

        endpointRepository.save(endpoint);
        resultRepository.save(result);
    }
}

