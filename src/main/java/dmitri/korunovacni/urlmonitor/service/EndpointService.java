package dmitri.korunovacni.urlmonitor.service;

import dmitri.korunovacni.urlmonitor.exception.RequestFormatException;
import dmitri.korunovacni.urlmonitor.exception.ResourceNotFoundException;
import dmitri.korunovacni.urlmonitor.model.dto.EndpointDto;
import dmitri.korunovacni.urlmonitor.model.dto.EndpointRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EndpointService {

    ResponseEntity<EndpointDto> createEndpoint(EndpointRequest request) throws RequestFormatException, ResourceNotFoundException;

    ResponseEntity<EndpointRequest> getEndpointById(Long id) throws RequestFormatException, ResourceNotFoundException;

    void deleteById(Long Id) throws ResourceNotFoundException;

    ResponseEntity<EndpointDto> editEndpointById(EndpointRequest request, Long id) throws RequestFormatException, ResourceNotFoundException;

    ResponseEntity<List<EndpointDto>> getEndpointsByUserId(Long id) throws RequestFormatException, ResourceNotFoundException;
}
