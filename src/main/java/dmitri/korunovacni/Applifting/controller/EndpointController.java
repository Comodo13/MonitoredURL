package dmitri.korunovacni.Applifting.controller;

import dmitri.korunovacni.Applifting.model.MonitoredEndpoint;
import dmitri.korunovacni.Applifting.model.dto.EndpointDto;
import dmitri.korunovacni.Applifting.model.dto.EndpointRequest;
import dmitri.korunovacni.Applifting.model.transformer.EndpointTransformer;
import dmitri.korunovacni.Applifting.service.EndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/endpoints")
public class EndpointController {

    @Autowired
    private final EndpointService endpointService;
    @Autowired
    public EndpointController(EndpointService endpointService) {
        this.endpointService = endpointService;
    }

    @PostMapping
    public ResponseEntity<EndpointRequest> createEndpoint(@RequestBody EndpointRequest request) {
        MonitoredEndpoint endpoint = endpointService.createEndpoint(request);
        EndpointRequest response = EndpointTransformer.toRequest(endpoint);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EndpointRequest> editEndpointByID(@RequestBody EndpointRequest request,@PathVariable Long id) {
        MonitoredEndpoint monitoredEndpoint = endpointService.editEndpoint(request, id);
        EndpointRequest response = EndpointTransformer.toRequest(monitoredEndpoint);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/{id}")
    public void deleteEndpoint(@PathVariable Long id) {
        endpointService.deleteById(id);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<EndpointDto>> getEndpointsByUserId(@PathVariable Long id) {
        List<MonitoredEndpoint> monitoredEndpoints = endpointService.getByUserId(id);
        List<EndpointDto> endpointDtos = monitoredEndpoints
                .stream()
                .map(x->EndpointTransformer.toDto(x))
                .collect(Collectors.toList());
        return new ResponseEntity<>(endpointDtos, HttpStatus.OK);
    }


}
