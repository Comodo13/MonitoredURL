package dmitri.korunovacni.Applifting.service;

import dmitri.korunovacni.Applifting.model.MonitoredEndpoint;
import dmitri.korunovacni.Applifting.model.dto.EndpointRequest;

import java.util.List;

public interface EndpointService {

    MonitoredEndpoint createEndpoint(EndpointRequest request);

    MonitoredEndpoint getById(Long id);

    void deleteById(Long Id);

    MonitoredEndpoint editEndpoint(EndpointRequest request, Long id);

    List<MonitoredEndpoint> getByUserId(Long id);
}
