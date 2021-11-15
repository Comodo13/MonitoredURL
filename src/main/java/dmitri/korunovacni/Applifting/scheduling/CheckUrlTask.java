package dmitri.korunovacni.Applifting.scheduling;

import dmitri.korunovacni.Applifting.model.MonitoredEndpoint;
import dmitri.korunovacni.Applifting.service.MonitoredResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import java.util.TimerTask;

public class CheckUrlTask extends TimerTask {
    @Autowired
    private MonitoredResultServiceImpl resultService;
    @Autowired
    private MonitoredEndpoint endpoint;

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

        if (payload !=null) {
        resultService.createResult(payload, statusCode, endpoint);
        }
    }
}
