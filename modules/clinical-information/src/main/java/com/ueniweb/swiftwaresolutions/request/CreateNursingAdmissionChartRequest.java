package com.ueniweb.swiftwaresolutions.request;

import lombok.Data;
import java.util.List;

@Data
public class CreateNursingAdmissionChartRequest {

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
    private List<CreateDiagnosisDetailsRequest> createDiagnosisDetailsRequestList;


}