package com.ueniweb.swiftwaresolutions.domain;

import com.ueniweb.swiftwaresolutions.request.CreateDiagnosisDetailsRequest;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cli_patient_diagnosis")
@Data
public class NursingDiagnosisDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patId", nullable = false)
    private Long patId;

    @Column(name = "vstId", nullable = false)
    private Long vstId;

    @Column(name = "ipId")
    private Long ipId;

    @Column(name = "caseSheetType")
    private Long caseSheetType;

    @Column(name = "caseSheetId")
    private Long caseSheetId;

    @Column(name = "diagnosisId")
    private Long diagnosisId;

    @Column(name = "isValid")
    private Long isValid;

    public static NursingDiagnosisDetails fromRequest(final Long defaultPatId, final Long defaultVstId, final Long defaultIpId, final CreateDiagnosisDetailsRequest dr) {
        NursingDiagnosisDetails nd = new NursingDiagnosisDetails();
        nd.setPatId(dr != null && dr.getPatId() != null ? dr.getPatId() : defaultPatId);
        nd.setVstId(dr != null && dr.getVstId() != null ? dr.getVstId() : defaultVstId);
        nd.setIpId(dr != null && dr.getIpId() != null ? dr.getIpId() : defaultIpId);
        nd.setCaseSheetType(dr != null && dr.getCaseSheetType() != null ? dr.getCaseSheetType() : 11L);
        nd.setCaseSheetId(dr != null && dr.getCaseSheetId() != null ? dr.getCaseSheetId() : 0L);
        nd.setDiagnosisId(dr != null ? dr.getDiagnosisId() : null);
        nd.setIsValid(dr != null && dr.getIsValid() != null ? dr.getIsValid() : 1L);
        return nd;
    }
}
