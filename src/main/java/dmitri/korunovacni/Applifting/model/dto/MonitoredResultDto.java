package dmitri.korunovacni.Applifting.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MonitoredResultDto {
    private Long id;
    private LocalDateTime dateOfCheck;
    private int statusCode;
    private String returnedPayload;
    private Long endpointID;
}
