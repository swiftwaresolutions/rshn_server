package com.ueniweb.swiftwaresolutions.request;

import lombok.Data;
import java.util.List;

@Data
public class CreateNursingChartRequest {

    private Long patId;
    private Long visitId;
    private Long ipId;
    private String historyByNurse;
    private String provisionalDiagnosis;
    private List<NursingVitalDetailRequest> nursingVitalDetails;

    @Override
    public String toString() {
        return "CreateNursingChartRequest{" +
                "patId=" + patId +
                ", visitId=" + visitId +
                ", ipId=" + ipId +
                ", historyByNurse='" + historyByNurse + '\'' +
                ", provisionalDiagnosis='" + provisionalDiagnosis + '\'' +
                ", nursingVitalDetails=" + nursingVitalDetails +
                '}';
    }
}
