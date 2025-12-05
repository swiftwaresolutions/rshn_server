package com.ueniweb.swiftwaresolutions.domain;

import com.ueniweb.swiftwaresolutions.request.CreateNursingChartRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cli_nursing_chart")
public class NursingChart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patId", nullable = false)
    private Long patId;

    @Column(name = "visitId", nullable = false)
    private Long visitId;

    @Column(name = "ipId", nullable = false)
    private Long ipId;

    @Column(name = "historyByNurse", columnDefinition = "TEXT")
    private String historyByNurse;

    @Column(name = "provisionalDiagnosis", columnDefinition = "TEXT")
    private String provisionalDiagnosis;

    @Column(name = "entryDateTime")
    private LocalDateTime entryDateTime;

    @Column(name = "entryUserId")
    private Long entryUserId;

    @Column(name = "editDateTime")
    private LocalDateTime editDateTime;

    @Column(name = "editUserId")
    private Long editUserId;

    public static NursingChart to(final CreateNursingChartRequest request, final Long userId) {
        NursingChart chart = new NursingChart();
        
        chart.setPatId(request.getPatId());
        chart.setVisitId(request.getVisitId());
        chart.setIpId(request.getIpId());
        chart.setHistoryByNurse(request.getHistoryByNurse());
        chart.setProvisionalDiagnosis(request.getProvisionalDiagnosis());
        
        chart.setEntryDateTime(LocalDateTime.now());
        chart.setEntryUserId(userId);
        chart.setEditDateTime(LocalDateTime.now());
        chart.setEditUserId(userId);
        
        return chart;
    }

    public void update(final CreateNursingChartRequest request, final Long userId) {
        this.setPatId(request.getPatId());
        this.setVisitId(request.getVisitId());
        this.setIpId(request.getIpId());
        this.setHistoryByNurse(request.getHistoryByNurse());
        this.setProvisionalDiagnosis(request.getProvisionalDiagnosis());
        
        this.setEditDateTime(LocalDateTime.now());
        this.setEditUserId(userId);
    }
}
