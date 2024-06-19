package com.ueniweb.swiftwaresolutions.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GeneralCaseSheetRequest extends AbstractCaseSheetRequest {

    private Long patientId;

    private Long visitId;

    private Long ipId;

    private String investigationHistory;

    private String menstrualHistory;

    private String oralCavity;

    private String cvs;

    private String res;

    private String abdominal;

    private String cns;

    private String perVaginal;

    private String oralRectal;

    private String skin;

    private String musculoskeletal;

    private String additionalFindings;

    private String differentialDiagnosis;

    private String confirmedDiagnosis;

    private String medications;

    private String recomendations;

    private String proceduresPlanned;

    private String followUpPlan;

    private String examination;

    private String diagnosis;

    private String discussion;


    private List<CreateComplaintDetailsRequest> createComplaintDetailsRequestList;


    private List<CreateDiagnosisDetailsRequest> createDiagnosisDetailsRequestList;

    @Override
    public String toString() {
        return "GeneralCaseSheetRequest{" +
                "patientId=" + patientId +
                ", visitId=" + visitId +
                ", ipId=" + ipId +
                ", presentIllness='" + getPresentIllness() + '\'' +
                ", investigationHistory='" + investigationHistory + '\'' +
                ", menstrualHistory='" + menstrualHistory + '\'' +
                ", temperature='" + getTemperature() + '\'' +
                ", pulse='" + getPulse() + '\'' +
                ", rr='" + getRr() + '\'' +
                ", bp='" + getBp() + '\'' +
                ", spo2='" + getSpo2() + '\'' +
                ", height='" + getHeight() + '\'' +
                ", weight='" + getWeight() + '\'' +
                ", bmi='" + getBmi() + '\'' +
                ", oralCavity='" + oralCavity + '\'' +
                ", cvs='" + cvs + '\'' +
                ", rs='" + res + '\'' +
                ", abdominal='" + abdominal + '\'' +
                ", cns='" + cns + '\'' +
                ", perVaginal='" + perVaginal + '\'' +
                ", oralRectal='" + oralRectal + '\'' +
                ", skin='" + skin + '\'' +
                ", musculoskeletal='" + musculoskeletal + '\'' +
                ", additionalFindings='" + additionalFindings + '\'' +
                ", allergy='" + getAllergy() + '\'' +
                ", differentialDiagnosis='" + differentialDiagnosis + '\'' +
                ", confirmedDiagnosis='" + confirmedDiagnosis + '\'' +
                ", medications='" + medications + '\'' +
                ", recomendations='" + recomendations + '\'' +
                ", proceduresPlanned='" + proceduresPlanned + '\'' +
                ", followUpPlan='" + followUpPlan + '\'' +
                ", examination='" + examination + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", discussion='" + discussion + '\'' +
                '}';
    }
}
