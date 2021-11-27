package dmitri.korunovacni.urlmonitor.model.transformer;

import dmitri.korunovacni.urlmonitor.model.MonitoredResult;
import dmitri.korunovacni.urlmonitor.model.dto.MonitoredResultDto;

public class ResultTransformer {

    public static MonitoredResultDto toDto(MonitoredResult monitoredResult) {
        return MonitoredResultDto.builder()
                .endpointID(monitoredResult.getEndpointId())
                .id(monitoredResult.getId())
                .dateOfCheck(monitoredResult.getDateOfCheck())
                .returnedPayload(monitoredResult.getReturnedPayload())
                .statusCode(monitoredResult.getStatusCode())
                .build();
    }
}
