package dmitri.korunovacni.urlmonitor.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;

@Data
@Value
@Builder
public class EndpointDto {
    Long id;
    String name;
    String url;
    LocalDateTime dateOfCreation;
    LocalDateTime dateOfLastCheck;
    Long monitoredIntervalInSeconds;
    String userName;
}
