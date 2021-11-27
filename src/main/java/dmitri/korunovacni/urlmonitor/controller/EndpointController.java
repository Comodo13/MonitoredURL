package dmitri.korunovacni.urlmonitor.controller;

import dmitri.korunovacni.urlmonitor.exception.RequestFormatException;
import dmitri.korunovacni.urlmonitor.exception.ResourceNotFoundException;
import dmitri.korunovacni.urlmonitor.model.dto.EndpointDto;
import dmitri.korunovacni.urlmonitor.model.dto.EndpointRequest;
import dmitri.korunovacni.urlmonitor.service.EndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public ResponseEntity<EndpointDto> createEndpoint(@RequestBody EndpointRequest request)
            throws RequestFormatException, ResourceNotFoundException {
        return endpointService.createEndpoint(request);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EndpointDto> editEndpointByID(@RequestBody EndpointRequest request, @PathVariable Long id)
            throws RequestFormatException, ResourceNotFoundException {
        return endpointService.editEndpointById(request, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEndpoint(@PathVariable Long id) throws ResourceNotFoundException {
        endpointService.deleteById(id);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<EndpointDto>> getEndpointsByUserId(@PathVariable Long id)
            throws RequestFormatException, ResourceNotFoundException {
        return endpointService.getEndpointsByUserId(id);
    }


}
