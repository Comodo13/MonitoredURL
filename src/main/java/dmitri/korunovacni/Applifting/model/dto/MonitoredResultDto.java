package dmitri.korunovacni.Applifting.model.dto;

import dmitri.korunovacni.Applifting.model.MonitoredEndpoint;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
public class MonitoredResultDto {
    private Long id;
    private LocalDateTime dateOfCheck;
    private int statusCode;
    private String returnedPayload;
    private Long endpointID;
}
