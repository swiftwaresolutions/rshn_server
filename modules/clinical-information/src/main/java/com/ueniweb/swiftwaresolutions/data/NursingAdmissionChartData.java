
package com.ueniweb.swiftwaresolutions.data;

import lombok.Data;
import java.util.List;

@Data
public class NursingAdmissionChartData {
    private Long id;
    private Long patId;
    private Long visitId;
    private Long ipId;
    private Long doctorId;
    private String historyByDoctor;
    private String historyOfAllergy;
    private String bloodGroup;
    private String provisionalDiagnosis;
    private String dischargeDiagnosis;
    private String dateTime;
    private String department;
    private String medication;
    private String dischargeCondition;
    private String invProcedures;
    private String entryDateTime;
    private Long entryUserId;
    private String editDateTime;
    private Long editUserId;
    private List<DiagnosisData> diagnosisList;
}
