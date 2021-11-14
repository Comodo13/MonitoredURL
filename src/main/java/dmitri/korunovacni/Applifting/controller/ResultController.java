package dmitri.korunovacni.Applifting.controller;

import dmitri.korunovacni.Applifting.model.MonitoredResult;
import dmitri.korunovacni.Applifting.model.dto.MonitoredResultDto;
import dmitri.korunovacni.Applifting.model.transformer.ResultTransformer;
import dmitri.korunovacni.Applifting.service.MonitoredResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("api/v1/results")
public class ResultController {

    private final MonitoredResultService monitoredResultService;

    @Autowired
    public ResultController(MonitoredResultService monitoredResultService) {
        this.monitoredResultService = monitoredResultService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<MonitoredResultDto>> getLastTenResultsByEndpointId(@PathVariable Long id) {
       List<MonitoredResult> lastResults = monitoredResultService.getLastTenResultsByEndpointId(id);
       List<MonitoredResultDto> resultsDtos = lastResults
               .stream()
               .map(x-> ResultTransformer.toDto(x))
               .collect(Collectors.toList());
        return  new ResponseEntity<>(resultsDtos, HttpStatus.OK);
    }
}
