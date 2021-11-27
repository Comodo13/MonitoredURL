package dmitri.korunovacni.urlmonitor.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Setter
@Value
public class CustomExceptionBody {
    String message;
    ZonedDateTime time;
}
