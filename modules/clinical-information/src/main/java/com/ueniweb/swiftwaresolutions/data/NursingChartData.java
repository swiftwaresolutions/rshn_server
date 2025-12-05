package com.ueniweb.swiftwaresolutions.data;

import lombok.Data;

import java.util.List;

@Data
public class NursingChartData {

    private Long id;
    private Long patId;
    private Long visitId;
    private Long ipId;
    private String historyByNurse;
    private String provisionalDiagnosis;
    private String entryDateTime;
    private Long entryUserId;
    private String editDateTime;
    private Long editUserId;
    private List<NursingVitalDetailData> nursingVitalDetails;
}
