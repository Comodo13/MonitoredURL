package dmitri.korunovacni.Applifting.model.transformer;

import dmitri.korunovacni.Applifting.model.MonitoredEndpoint;
import dmitri.korunovacni.Applifting.model.dto.EndpointDto;
import dmitri.korunovacni.Applifting.model.dto.EndpointRequest;

public class EndpointTransformer {


    public static EndpointRequest toRequest(MonitoredEndpoint endpoint) {
        EndpointRequest request = new EndpointRequest();
        request.setName(endpoint.getName());
        request.setUrl(endpoint.getUrl());
        request.setUserId(endpoint.getUser().getId());
        request.setMonitoredIntervalInSeconds(endpoint.getMonitoredIntervalInSeconds().intValue());
        return request;

    }

    public static EndpointDto toDto(MonitoredEndpoint endpoint) {
        EndpointDto dto = new EndpointDto();
        dto.setId(endpoint.getId());
        dto.setName(endpoint.getName());
        dto.setUrl(endpoint.getUrl());
        dto.setUserName(endpoint.getUser().getUsername());
        dto.setDateOfCreation(endpoint.getDateOfCreation());
        dto.setDateOfLastCheck(endpoint.getDateOfLastCheck());
        dto.setMonitoredIntervalInSeconds(endpoint.getMonitoredIntervalInSeconds());

        return dto;
    }

}
