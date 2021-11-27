package dmitri.korunovacni.urlmonitor.controller;

import dmitri.korunovacni.urlmonitor.exception.RequestFormatException;
import dmitri.korunovacni.urlmonitor.exception.ResourceNotFoundException;
import dmitri.korunovacni.urlmonitor.model.dto.MonitoredResultDto;
import dmitri.korunovacni.urlmonitor.service.MonitoredResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/v1/results")
public class ResultController {

    private final MonitoredResultService monitoredResultService;

    @Autowired
    public ResultController(MonitoredResultService monitoredResultService) {
        this.monitoredResultService = monitoredResultService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<MonitoredResultDto>> getLastTenResultsByEndpointId(@PathVariable Long id)
            throws RequestFormatException, ResourceNotFoundException {
        return monitoredResultService.getLastTenResultsByEndpointId(id);
    }
}
