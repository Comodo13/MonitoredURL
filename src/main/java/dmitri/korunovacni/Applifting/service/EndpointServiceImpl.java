package dmitri.korunovacni.Applifting.service;

import dmitri.korunovacni.Applifting.model.MonitoredEndpoint;
import dmitri.korunovacni.Applifting.model.User;
import dmitri.korunovacni.Applifting.model.dto.EndpointRequest;
import dmitri.korunovacni.Applifting.repo.EndpointRepository;
import dmitri.korunovacni.Applifting.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EndpointServiceImpl implements EndpointService {

    private final EndpointRepository endpointRepository;
    private final UserRepository userRepository;
    private final MonitoredResultService monitoredResultService;

    @Autowired
    public EndpointServiceImpl(EndpointRepository endpointRepository, UserRepository userRepository,
                               MonitoredResultService monitoredResultService) {
        this.endpointRepository = endpointRepository;
        this.userRepository = userRepository;
        this.monitoredResultService = monitoredResultService;
    }



    //create endpoint and start scheduling on background
    @Override
    public MonitoredEndpoint createEndpoint(EndpointRequest request) {
        MonitoredEndpoint endpoint = new MonitoredEndpoint();


        if (request.getMonitoredIntervalInSeconds()==0) {
            endpoint.setMonitoredIntervalInSeconds(Long.valueOf(120)); //120s - default interval
        }
        else {
            endpoint.setMonitoredIntervalInSeconds((long) request.getMonitoredIntervalInSeconds());
        }
//        if (request.getUserId()==null) {
//            throw new UserNotFoundException("Enter userId");
//        }

//        if (userRepository.getById(Long.valueOf(request.getUserId())) == null) {
//            throw new UserNotFoundException("User with ID: " + request.getUserId() + "not found.");
//        }
        User user = new User();
        try {
         user = userRepository.getById(request.getUserId());
        }
        catch(Exception ex){
            System.err.println("Enter userId");
        }
//        if(userRepository.findById(request.getUserId()).get()==null) {
//            throw new UserNotFoundException("Enter userId");
//        }
//        else {
            endpoint.setName(request.getName());
            endpoint.setUrl(request.getUrl());
            endpoint.setUser(user);
            endpoint.setDateOfCreation(LocalDateTime.now());
            endpoint.setDateOfLastCheck(LocalDateTime.now());
            endpointRepository.save(endpoint);

            monitoredResultService.startScheduleChecking(endpoint);

            return endpoint;

    }

    @Override
    public MonitoredEndpoint getById(Long id) {
        return endpointRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        endpointRepository.deleteById(id);
    }

    @Override
    public MonitoredEndpoint editEndpoint(EndpointRequest request, Long id) {
        MonitoredEndpoint editing = endpointRepository.getById(id);
        if (request.getUrl() != null) {
            editing.setUrl(request.getUrl());
        }
        if (request.getUserId() != null) {
            editing.setUser(userRepository.getById(request.getUserId()));
        }
        if (request.getName() != null) {
            editing.setName(request.getName());
        }
        if (request.getMonitoredIntervalInSeconds()==0) {
            editing.setMonitoredIntervalInSeconds(Long.valueOf(120));
        }
        else if (request.getMonitoredIntervalInSeconds()>0) {
            editing.setMonitoredIntervalInSeconds(Long.valueOf(request.getMonitoredIntervalInSeconds()));
        }
            endpointRepository.save(editing);
            return editing;
    }

    @Override
    public List<MonitoredEndpoint> getByUserId(Long id) {
       return userRepository.getById(id).getEndpoints();
    }
}
