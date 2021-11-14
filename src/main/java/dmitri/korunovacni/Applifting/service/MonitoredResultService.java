package dmitri.korunovacni.Applifting.service;

import dmitri.korunovacni.Applifting.model.MonitoredEndpoint;
import dmitri.korunovacni.Applifting.model.MonitoredResult;


import java.util.List;


public interface MonitoredResultService {


    void startScheduleChecking(MonitoredEndpoint endpoint);
    List<MonitoredResult> getLastTenResultsByEndpointId(Long id);

    void createResult(String payload, int statusCode, MonitoredEndpoint endpoint);
}
