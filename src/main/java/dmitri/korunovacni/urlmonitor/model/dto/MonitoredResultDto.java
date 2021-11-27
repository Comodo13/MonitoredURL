package dmitri.korunovacni.urlmonitor.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;

@Data
@Value
@Builder
public class MonitoredResultDto {
    Long id;
    LocalDateTime dateOfCheck;
    int statusCode;
    String returnedPayload;
    Long endpointID;
}
