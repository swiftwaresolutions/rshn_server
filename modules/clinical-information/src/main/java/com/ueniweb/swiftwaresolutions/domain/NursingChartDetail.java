package com.ueniweb.swiftwaresolutions.domain;

import com.ueniweb.swiftwaresolutions.request.NursingVitalDetailRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "cli_nursing_chart_details")
public class NursingChartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chartId", nullable = false)
    private Long chartId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "temp", length = 36)
    private String temp;

    @Column(name = "pulse", length = 36)
    private String pulse;

    @Column(name = "respRate", length = 36)
    private String respRate;

    @Column(name = "bp", length = 36)
    private String bp;

    @Column(name = "bowels", length = 36)
    private String bowels;

    @Column(name = "urine", length = 36)
    private String urine;

    @Column(name = "nurseRecords", columnDefinition = "TEXT")
    private String nurseRecords;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "isValid")
    private Integer isValid;

    public static NursingChartDetail fromRequest(final NursingVitalDetailRequest request) {
        NursingChartDetail detail = new NursingChartDetail();
        
        if (request.getDate() != null && !request.getDate().isEmpty()) {
            detail.setDate(LocalDate.parse(request.getDate()));
        }
        
        detail.setTemp(request.getTemp());
        detail.setPulse(request.getPulse());
        detail.setRespRate(request.getRespRate());
        detail.setBp(request.getBp());
        detail.setBowels(request.getBowels());
        detail.setUrine(request.getUrine());
        detail.setNurseRecords(request.getNurseRecords());
        
        if (request.getTime() != null && !request.getTime().isEmpty()) {
            detail.setTime(LocalTime.parse(request.getTime()));
        }
        
        detail.setIsValid(request.getIsValid() != null ? request.getIsValid() : 1);
        
        return detail;
    }

    public void update(final NursingVitalDetailRequest request) {
        if (request.getDate() != null && !request.getDate().isEmpty()) {
            this.date = LocalDate.parse(request.getDate());
        }
        
        this.temp = request.getTemp();
        this.pulse = request.getPulse();
        this.respRate = request.getRespRate();
        this.bp = request.getBp();
        this.bowels = request.getBowels();
        this.urine = request.getUrine();
        this.nurseRecords = request.getNurseRecords();
        
        if (request.getTime() != null && !request.getTime().isEmpty()) {
            this.time = LocalTime.parse(request.getTime());
        }
        
        this.isValid = request.getIsValid() != null ? request.getIsValid() : 1;
    }
}
