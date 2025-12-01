package com.ueniweb.swiftwaresolutions.domain;

import com.ueniweb.swiftwaresolutions.request.CreateNursingAdmissionChartRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "cli_nursing_admission_chart")
public class NursingAdmissionChart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patId", nullable = false)
    @Basic(optional = false)
    private Long patId;

    @Column(name = "visitId", nullable = false)
    @Basic(optional = false)
    private Long visitId;

    @Column(name = "ipId", nullable = false)
    @Basic(optional = false)
    private Long ipId;

    @Column(name = "doctorId")
    private Long doctorId;

    @Column(name = "historyByDoctor", columnDefinition = "TEXT")
    private String historyByDoctor;

    @Column(name = "historyOfAllergy", columnDefinition = "TEXT")
    private String historyOfAllergy;

    @Column(name = "bloodGroup", length = 32)
    private String bloodGroup;

    @Column(name = "provisionalDiagnosis", columnDefinition = "TEXT")
    private String provisionalDiagnosis;

    @Column(name = "dischargeDiagnosis", columnDefinition = "TEXT")
    private String dischargeDiagnosis;

    @Column(name = "dateTime")
    private LocalDateTime dateTime;

    @Column(name = "department", columnDefinition = "TEXT")
    private String department;

    @Column(name = "medication", columnDefinition = "TEXT")
    private String medication;

    @Column(name = "dischargeCondition", length = 32)
    private String dischargeCondition;

    @Column(name = "invProcedures", columnDefinition = "TEXT")
    private String invProcedures;

    @Column(name = "entryDateTime")
    private LocalDateTime entryDateTime;

    @Column(name = "entryUserId")
    private Long entryUserId ;

    @Column(name = "editDateTime")
    private LocalDateTime editDateTime;

    @Column(name = "editUserId")
    private Long editUserId ;

    @Transient
    private List<DiagnosisDetails> diagnosisDetailsList = new ArrayList<>();

    public static NursingAdmissionChart to(final CreateNursingAdmissionChartRequest createNursingAdmissionChartRequest,final Long userId) {
        NursingAdmissionChart nursingAdmissionChart = new NursingAdmissionChart();

        nursingAdmissionChart.setPatId(createNursingAdmissionChartRequest.getPatId());
        nursingAdmissionChart.setVisitId(createNursingAdmissionChartRequest.getVisitId());
        nursingAdmissionChart.setIpId(createNursingAdmissionChartRequest.getIpId());
        nursingAdmissionChart.setDoctorId(createNursingAdmissionChartRequest.getDoctorId());
        nursingAdmissionChart.setHistoryByDoctor(createNursingAdmissionChartRequest.getHistoryByDoctor());
        nursingAdmissionChart.setHistoryOfAllergy(createNursingAdmissionChartRequest.getHistoryOfAllergy());
        nursingAdmissionChart.setBloodGroup(createNursingAdmissionChartRequest.getBloodGroup());
        nursingAdmissionChart.setProvisionalDiagnosis(createNursingAdmissionChartRequest.getProvisionalDiagnosis());
        nursingAdmissionChart.setDischargeDiagnosis(createNursingAdmissionChartRequest.getDischargeDiagnosis());

        if (createNursingAdmissionChartRequest.getDateTime() != null && !createNursingAdmissionChartRequest.getDateTime().isEmpty()) {
            nursingAdmissionChart.setDateTime(LocalDateTime.parse(createNursingAdmissionChartRequest.getDateTime()));
        }

        nursingAdmissionChart.setDepartment(createNursingAdmissionChartRequest.getDepartment());
        nursingAdmissionChart.setMedication(createNursingAdmissionChartRequest.getMedication());
        nursingAdmissionChart.setDischargeCondition(createNursingAdmissionChartRequest.getDischargeCondition());
        nursingAdmissionChart.setInvProcedures(createNursingAdmissionChartRequest.getInvProcedures());
        nursingAdmissionChart.setEntryDateTime(LocalDateTime.now());
        nursingAdmissionChart.setEditDateTime(LocalDateTime.now());

        nursingAdmissionChart.setEntryUserId(userId);
        nursingAdmissionChart.setEditUserId(userId);

        return nursingAdmissionChart;
    }

    public void update(final CreateNursingAdmissionChartRequest createNursingAdmissionChartRequest,final Long userId) {
        this.setPatId(createNursingAdmissionChartRequest.getPatId());
        this.setVisitId(createNursingAdmissionChartRequest.getVisitId());
        this.setIpId(createNursingAdmissionChartRequest.getIpId());
        this.setDoctorId(createNursingAdmissionChartRequest.getDoctorId());
        this.setHistoryByDoctor(createNursingAdmissionChartRequest.getHistoryByDoctor());
        this.setHistoryOfAllergy(createNursingAdmissionChartRequest.getHistoryOfAllergy());
        this.setBloodGroup(createNursingAdmissionChartRequest.getBloodGroup());
        this.setProvisionalDiagnosis(createNursingAdmissionChartRequest.getProvisionalDiagnosis());
        this.setDischargeDiagnosis(createNursingAdmissionChartRequest.getDischargeDiagnosis());

        if (createNursingAdmissionChartRequest.getDateTime() != null && !createNursingAdmissionChartRequest.getDateTime().isEmpty()) {
            this.setDateTime(LocalDateTime.parse(createNursingAdmissionChartRequest.getDateTime()));
        }

        this.setDepartment(createNursingAdmissionChartRequest.getDepartment());
        this.setMedication(createNursingAdmissionChartRequest.getMedication());
        this.setDischargeCondition(createNursingAdmissionChartRequest.getDischargeCondition());
        this.setInvProcedures(createNursingAdmissionChartRequest.getInvProcedures());

        this.setEditDateTime(LocalDateTime.now());
        this.setEditUserId(userId);
    }
}