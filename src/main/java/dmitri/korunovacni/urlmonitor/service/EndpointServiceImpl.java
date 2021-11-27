package dmitri.korunovacni.urlmonitor.service;

import dmitri.korunovacni.urlmonitor.exception.RequestFormatException;
import dmitri.korunovacni.urlmonitor.exception.ResourceNotFoundException;
import dmitri.korunovacni.urlmonitor.model.MonitoredEndpoint;
import dmitri.korunovacni.urlmonitor.model.User;
import dmitri.korunovacni.urlmonitor.model.dto.EndpointDto;
import dmitri.korunovacni.urlmonitor.model.dto.EndpointRequest;
import dmitri.korunovacni.urlmonitor.model.transformer.EndpointTransformer;
import dmitri.korunovacni.urlmonitor.repo.EndpointRepository;
import dmitri.korunovacni.urlmonitor.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EndpointServiceImpl implements EndpointService {

    private final EndpointRepository endpointRepository;
    private final UserRepository userRepository;
    private final MonitoredResultService monitoredResultService;

    /**
     * Create endpoint and start scheduling on background
     **/
    @Override
    public ResponseEntity<EndpointDto> createEndpoint(EndpointRequest request)
            throws RequestFormatException, ResourceNotFoundException {
        MonitoredEndpoint endpoint;
        User user;
        if (request.getUserId() == null || request.getUserId() == 0L) {
            throw new RequestFormatException("User Id can't be zero or null");
        }
        Optional<User> userOpt = userRepository.findById(request.getUserId());
        if (userOpt.isEmpty()) {
            throw new ResourceNotFoundException("User with ID: " + request.getUserId() + " was not found");
        } else user = userOpt.get();

        endpoint = EndpointTransformer.toEntity(request, user);

        endpointRepository.save(endpoint);
        monitoredResultService.startScheduleChecking(endpoint);


        EndpointDto dto = EndpointTransformer.toDto(endpoint);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<EndpointRequest> getEndpointById(Long id) throws RequestFormatException, ResourceNotFoundException {
        if (id == null || id == 0L) {
            throw new RequestFormatException("Endpoint Id can't be zero or null");
        }
        MonitoredEndpoint monitoredEndpoint;
        Optional<MonitoredEndpoint> endpointOpt = endpointRepository.findById(id);

        if (endpointOpt.isPresent()) {
            monitoredEndpoint = endpointOpt.get();
        } else throw new ResourceNotFoundException("Endpoint with ID: " + id + " was not found");

        EndpointRequest response = EndpointTransformer.toRequest(monitoredEndpoint);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (endpointRepository.existsById(id)) {
            endpointRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Endpoint with ID: " + id + " was not found");
        }
    }

    @Override
    public ResponseEntity<EndpointDto> editEndpointById(EndpointRequest request, Long id)
            throws RequestFormatException, ResourceNotFoundException {
        MonitoredEndpoint editing;

        if (id == null || id == 0L) {
            throw new RequestFormatException("Endpoint Id can't be zero or null");
        }
        Optional<MonitoredEndpoint> editingOpt = endpointRepository.findById(id);
        if (editingOpt.isEmpty()) {
            throw new ResourceNotFoundException("Endpoint with ID: " + id + " was not found");
        } else {
            editing = editingOpt.get();
        }

        if (request.getUrl() != null) {
            editing.setUrl(request.getUrl());
        }
        if (request.getUserId() != null) {
            editing.setUser(userRepository.getById(request.getUserId()));
        }
        if (request.getName() != null) {
            editing.setName(request.getName());
        }
        if (request.getMonitoredIntervalInSeconds() == 0) {
            editing.setMonitoredIntervalInSeconds(120L);  ///todo
        } else if (request.getMonitoredIntervalInSeconds() > 0) {
            editing.setMonitoredIntervalInSeconds((long) request.getMonitoredIntervalInSeconds());
        }
        endpointRepository.save(editing);
        EndpointDto dto = EndpointTransformer.toDto(editing);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EndpointDto>> getEndpointsByUserId(Long id) throws RequestFormatException, ResourceNotFoundException {
        if (id == null || id == 0L) {
            throw new RequestFormatException("User Id can't be zero or null");
        }
        User user;
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            user = userOpt.get();
        } else throw new ResourceNotFoundException("Endpoint with ID: " + id + " was not found");

        List<MonitoredEndpoint> monitoredEndpoints = user.getEndpoints();
        List<EndpointDto> endpointDtos = monitoredEndpoints
                .stream()
                .map(EndpointTransformer::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(endpointDtos, HttpStatus.OK);
    }
}
