package dmitri.korunovacni.urlmonitor.service;

import dmitri.korunovacni.urlmonitor.exception.RequestFormatException;
import dmitri.korunovacni.urlmonitor.exception.ResourceNotFoundException;
import dmitri.korunovacni.urlmonitor.model.MonitoredEndpoint;
import dmitri.korunovacni.urlmonitor.model.MonitoredResult;
import dmitri.korunovacni.urlmonitor.repo.EndpointRepository;
import dmitri.korunovacni.urlmonitor.repo.ResultRepository;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import java.util.List;



import static org.mockito.Mockito.when;


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
    void getLastTenResultsByEndpointId() throws RequestFormatException, ResourceNotFoundException {
        Long id = 1L;
        for(int i =0;i<15;i++) {
            results.add(new MonitoredResult());
        }

        MonitoredEndpoint endpoint = new MonitoredEndpoint();
        endpoint.setId(id);
        endpoint.setResults(results);

        when(endpointRepository.getById(id)).thenReturn(endpoint);
        Assert.assertEquals(monitoredResultService.getLastTenResultsByEndpointId(id).getBody().size(),10);
    }

}