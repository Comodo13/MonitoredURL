package dmitri.korunovacni.Applifting.service;

import dmitri.korunovacni.Applifting.model.MonitoredEndpoint;
import dmitri.korunovacni.Applifting.model.User;
import dmitri.korunovacni.Applifting.model.dto.EndpointRequest;
import dmitri.korunovacni.Applifting.repo.EndpointRepository;
import dmitri.korunovacni.Applifting.repo.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

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
    void createEndpoint() {

        EndpointRequest request = new EndpointRequest();
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

        Assert.assertEquals(endpointServiceImpl.createEndpoint(request).getUrl(),monitoredEndpoint.getUrl());
        Assert.assertEquals(endpointServiceImpl.createEndpoint(request).getName(),monitoredEndpoint.getName());
    }
    @Test
    void createEndpointIfGivenIntervalEqualsZero() {
        EndpointRequest request = new EndpointRequest();
        request.setUserId(1L);

        request.setMonitoredIntervalInSeconds(0);
        User user = new User();
        user.setId(1L);
        Mockito.when(userRepository.getById(request.getUserId())).thenReturn(user);
        Assert.assertEquals(endpointServiceImpl.createEndpoint(request).getMonitoredIntervalInSeconds().intValue(),120);
    }

    @Test
    void editEndpoint() {
        EndpointRequest request = new EndpointRequest();
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
        Assert.assertEquals(endpointServiceImpl.editEndpoint(request, id).getMonitoredIntervalInSeconds(),edited.getMonitoredIntervalInSeconds());
        Assert.assertEquals(endpointServiceImpl.editEndpoint(request, id).getUrl(),edited.getUrl());
        Assert.assertEquals(endpointServiceImpl.editEndpoint(request, id).getName(),edited.getName());
    }

}