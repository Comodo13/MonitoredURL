package dmitri.korunovacni.urlmonitor.service;

import dmitri.korunovacni.urlmonitor.exception.RequestFormatException;
import dmitri.korunovacni.urlmonitor.exception.ResourceNotFoundException;
import dmitri.korunovacni.urlmonitor.model.MonitoredEndpoint;
import dmitri.korunovacni.urlmonitor.model.dto.MonitoredResultDto;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface MonitoredResultService {


    void startScheduleChecking(MonitoredEndpoint endpoint);

    ResponseEntity<List<MonitoredResultDto>> getLastTenResultsByEndpointId(Long id) throws RequestFormatException, ResourceNotFoundException;

    void createResult(String payload, int statusCode, MonitoredEndpoint endpoint);
}
