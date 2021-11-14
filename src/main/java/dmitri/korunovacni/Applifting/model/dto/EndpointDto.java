package dmitri.korunovacni.Applifting.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EndpointDto {
    private Long id;
    private String name;
    private String url;
    private LocalDateTime dateOfCreation;
    private LocalDateTime dateOfLastCheck;
    private Long monitoredIntervalInSeconds;
    private String userName;
}
