package dmitri.korunovacni.Applifting.model.dto;

import lombok.*;

@Data
public class EndpointRequest {
    private String name;
    private String url;
    private int monitoredIntervalInSeconds;
    private Long userId;
}
