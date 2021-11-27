package dmitri.korunovacni.urlmonitor.service;

import dmitri.korunovacni.urlmonitor.exception.RequestFormatException;
import dmitri.korunovacni.urlmonitor.exception.ResourceNotFoundException;
import dmitri.korunovacni.urlmonitor.model.MonitoredEndpoint;
import dmitri.korunovacni.urlmonitor.model.User;
import dmitri.korunovacni.urlmonitor.model.dto.EndpointRequest;
import dmitri.korunovacni.urlmonitor.repo.EndpointRepository;
import dmitri.korunovacni.urlmonitor.repo.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EndpointServiceImplTest {

    @InjectMocks
    EndpointServiceImpl endpointServiceImpl;

    @Mock
    EndpointRepository endpointRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    MonitoredResultService monitoredResultService;

    @Test
    void createEndpoint() throws RequestFormatException, ResourceNotFoundException {

        EndpointRequest request = EndpointRequest.builder()
                .userId(1L)
                .url("www.x.com")
                .name("first")
                .monitoredIntervalInSeconds(60)
                .build();
        request.setUserId(1L);
        request.setUrl("www.x.com");
        request.setName("first");
        request.setMonitoredIntervalInSeconds(60);

        User user = new User();
        user.setId(1L);

        MonitoredEndpoint monitoredEndpoint = new MonitoredEndpoint();
        monitoredEndpoint.setMonitoredIntervalInSeconds(60L);
        monitoredEndpoint.setName("first");
        monitoredEndpoint.setUser(user);
        monitoredEndpoint.setUrl("www.x.com");

        Mockito.when(userRepository.getById(request.getUserId())).thenReturn(user);

        assertEquals(endpointServiceImpl.createEndpoint(request).getBody().getUrl(),monitoredEndpoint.getUrl());
        assertEquals(endpointServiceImpl.createEndpoint(request).getBody().getName(),monitoredEndpoint.getName());
    }
    @Test
    void createEndpointIfGivenIntervalEqualsZero() throws RequestFormatException, ResourceNotFoundException {
        EndpointRequest request = EndpointRequest.builder().userId(1L).monitoredIntervalInSeconds(0).build();

        User user = new User();
        user.setId(1L);
        Mockito.when(userRepository.getById(request.getUserId())).thenReturn(user);
        Assert.assertEquals(endpointServiceImpl.createEndpoint(request).getBody().getMonitoredIntervalInSeconds().intValue(),120);
    }

    @Test
    void editEndpoint() throws RequestFormatException, ResourceNotFoundException {
        EndpointRequest request = EndpointRequest.builder()
                .monitoredIntervalInSeconds(30)
                .name("endpointFromRequest")
                .url("www.x.com")
                .build();
        request.setMonitoredIntervalInSeconds(30);
        request.setName("endpointFromRequest");
        request.setUrl("www.x.com");
        Long id = 1L;
        MonitoredEndpoint editing = new MonitoredEndpoint();
        editing.setUrl("www.lalala.com");
        editing.setName("endpointFromDB");
        editing.setMonitoredIntervalInSeconds(100L);

        MonitoredEndpoint edited = new MonitoredEndpoint();
        edited.setUrl("www.x.com");
        edited.setName("endpointFromRequest");
        edited.setMonitoredIntervalInSeconds(30L);

        Mockito.when(endpointRepository.getById(id)).thenReturn(editing);
        Assert.assertEquals(endpointServiceImpl.editEndpointById(request, id).getBody().getMonitoredIntervalInSeconds(),edited.getMonitoredIntervalInSeconds());
        Assert.assertEquals(endpointServiceImpl.editEndpointById(request, id).getBody().getUrl(),edited.getUrl());
        Assert.assertEquals(endpointServiceImpl.editEndpointById(request, id).getBody().getName(),edited.getName());
    }

}