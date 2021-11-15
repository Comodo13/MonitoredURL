package dmitri.korunovacni.Applifting.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EndpointDto {
    private Long id;
    private String name;
    private String url;
    private LocalDateTime dateOfCreation;
    private LocalDateTime dateOfLastCheck;
    private Long monitoredIntervalInSeconds;
    private String userName;
}
