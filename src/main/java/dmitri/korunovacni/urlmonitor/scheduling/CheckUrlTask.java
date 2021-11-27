package dmitri.korunovacni.urlmonitor.scheduling;

import dmitri.korunovacni.urlmonitor.model.MonitoredEndpoint;
import dmitri.korunovacni.urlmonitor.service.MonitoredResultServiceImpl;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.TimerTask;

@EqualsAndHashCode(callSuper = true)
@Value
public class CheckUrlTask extends TimerTask {
    @Autowired
    MonitoredResultServiceImpl resultService;
    @Autowired
    MonitoredEndpoint endpoint;

    public CheckUrlTask(MonitoredResultServiceImpl resultService, MonitoredEndpoint endpoint) {
        this.resultService = resultService;
        this.endpoint = endpoint;
    }

    @Override
    public void run() {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(endpoint.getUrl(), String.class);
        int statusCode = response.getStatusCode().value();

        String payload = response.getBody();
        resultService.createResult(Objects.requireNonNullElse(payload, "ERROR"), statusCode, endpoint);
    }
}
