package dmitri.korunovacni.Applifting.model.transformer;

import dmitri.korunovacni.Applifting.model.MonitoredResult;
import dmitri.korunovacni.Applifting.model.dto.MonitoredResultDto;

public class ResultTransformer {

    public static MonitoredResultDto toDto(MonitoredResult monitoredResult) {
        MonitoredResultDto resultDto = new MonitoredResultDto();
        resultDto.setEndpointID(monitoredResult.getEndpointId());
        resultDto.setId(monitoredResult.getId());
        resultDto.setDateOfCheck(monitoredResult.getDateOfCheck());
        resultDto.setReturnedPayload(monitoredResult.getReturnedPayload());
        resultDto.setStatusCode(monitoredResult.getStatusCode());

        return  resultDto;
    }
}
