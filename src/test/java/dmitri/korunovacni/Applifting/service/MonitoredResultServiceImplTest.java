package dmitri.korunovacni.Applifting.service;

import dmitri.korunovacni.Applifting.model.MonitoredEndpoint;
import dmitri.korunovacni.Applifting.model.MonitoredResult;
import dmitri.korunovacni.Applifting.repo.EndpointRepository;
import dmitri.korunovacni.Applifting.repo.ResultRepository;

import dmitri.korunovacni.Applifting.scheduling.CheckUrlTask;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import java.util.Timer;


import static org.mockito.Mockito.when;

//@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class MonitoredResultServiceImplTest {

    @InjectMocks
    MonitoredResultServiceImpl monitoredResultService;
    @Mock
    ResultRepository resultRepository;
    @Mock
    EndpointRepository endpointRepository;


    List<MonitoredResult> results = new ArrayList<>();


    @Test
    void getLastTenResultsByEndpointId() {
        Long id = 1L;
        for(int i =0;i<15;i++) {
            results.add(new MonitoredResult());
        }

        MonitoredEndpoint endpoint = new MonitoredEndpoint();
        endpoint.setId(id);
        endpoint.setResults(results);

        when(endpointRepository.getById(id)).thenReturn(endpoint);
        Assert.assertEquals(monitoredResultService.getLastTenResultsByEndpointId(id).size(),10);
    }

    @Test
    void createResult() {

//        //String payload, int statusCode, MonitoredEndpoint endpoint) {
//            LocalDateTime dateOfCheck = LocalDateTime.now();
//            MonitoredResult result = new MonitoredResult();
//            result.setReturnedPayload(payload.substring(0,1000)); //shortened payload to 1000 elements
//            result.setStatusCode(statusCode);
//            result.setDateOfCheck(dateOfCheck);
//            result.setEndpointId(endpoint.getId());
//
//            endpoint.setDateOfLastCheck(dateOfCheck);
//
//            endpointRepository.save(endpoint);
//            resultRepository.save(result);
    }

    @Test
    void startScheduleChecking() {
//        MonitoredEndpoint monitoredEndpoint = new MonitoredEndpoint();
//        monitoredEndpoint.setMonitoredIntervalInSeconds(60L);
//        Timer timer = new Timer();
//        Mockito.when()
//        CheckUrlTask check = new CheckUrlTask(new MonitoredResultServiceImpl(
//                resultRepository, endpointRepository), endpoint);
//        timer.schedule(check, 0, endpoint.getMonitoredIntervalInSeconds()*1000);
    }
}