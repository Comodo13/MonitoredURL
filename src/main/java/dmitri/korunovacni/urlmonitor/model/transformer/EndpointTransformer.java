package dmitri.korunovacni.urlmonitor.model.transformer;

import dmitri.korunovacni.urlmonitor.model.MonitoredEndpoint;
import dmitri.korunovacni.urlmonitor.model.User;
import dmitri.korunovacni.urlmonitor.model.dto.EndpointDto;
import dmitri.korunovacni.urlmonitor.model.dto.EndpointRequest;

import java.time.LocalDateTime;

public class EndpointTransformer {


    public static EndpointRequest toRequest(MonitoredEndpoint endpoint) {
        return EndpointRequest.builder()
                .name(endpoint.getName())
                .url(endpoint.getUrl())
                .userId(endpoint.getUser().getId())
                .monitoredIntervalInSeconds(endpoint.getMonitoredIntervalInSeconds().intValue())
                .build();
    }

    public static EndpointDto toDto(MonitoredEndpoint endpoint) {
        return EndpointDto.builder()
                .id(endpoint.getId())
                .name(endpoint.getName())
                .url(endpoint.getUrl())
                .userName(endpoint.getUser().getUsername())
                .dateOfCreation(endpoint.getDateOfCreation())
                .dateOfLastCheck(endpoint.getDateOfLastCheck())
                .monitoredIntervalInSeconds(endpoint.getMonitoredIntervalInSeconds())
                .build();
    }

    public static MonitoredEndpoint toEntity(EndpointRequest request, User user) {
        MonitoredEndpoint endpoint = new MonitoredEndpoint();
        if (request.getMonitoredIntervalInSeconds() == 0) {
            endpoint.setMonitoredIntervalInSeconds(120L); //120s - default interval
        } else {
            endpoint.setMonitoredIntervalInSeconds((long) request.getMonitoredIntervalInSeconds());
        }
        endpoint.setName(request.getName());
        endpoint.setUrl(request.getUrl());
        endpoint.setUser(user);
        endpoint.setDateOfCreation(LocalDateTime.now());
        endpoint.setDateOfLastCheck(LocalDateTime.now());

        return endpoint;
    }

}
