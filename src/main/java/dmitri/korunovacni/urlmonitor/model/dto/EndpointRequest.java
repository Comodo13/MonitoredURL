package dmitri.korunovacni.urlmonitor.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EndpointRequest {
    String name;
    String url;
    int monitoredIntervalInSeconds;
    Long userId;
}
