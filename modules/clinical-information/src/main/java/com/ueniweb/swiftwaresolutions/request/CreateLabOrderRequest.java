package com.ueniweb.swiftwaresolutions.request;

import com.ueniweb.swiftwaresolutions.utils.DateTimeUtils;
import lombok.Data;

import java.sql.Timestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Data
public class CreateLabOrderRequest {

    private Long orderId;

    private Long testId;

    private String testCode;

    private Long deptId;

    private Double rate;

    private Double disc;

    private Long resultDone;

    private String resultDate;

    private String  resultTime;

    private Long resultUid;

    private Long unit;

    private Long tempOrderId;

    private Long returnUnit;

    private Double fcRate;

    private Long patId;

    private Long uid;

    private String dtm;

    private Long headId;

    private Long finalBillId;

    @Override
    public String toString() {
        return "LabOrderRequest{" +
                "orderId=" + orderId +
                ", testId=" + testId +
                ", testCode='" + testCode + '\'' +
                ", deptId=" + deptId +
                ", rate=" + rate +
                ", disc=" + disc +
                ", resultDone=" + resultDone +
                ", result_date='" + resultDate+ '\'' +
                ", resultTime='" + resultTime + '\'' +
                ", resultUid=" + resultUid +
                ", unit=" + unit +
                ", tempOrderId=" + tempOrderId +
                ", returnUnit=" + returnUnit +
                ", fcRate=" + fcRate +
                ", patId=" + patId +
                ", uid=" + uid +
                ", dtm='" + dtm+ '\'' +
                ", headId=" + headId +
                ", finalBillId=" + finalBillId +
                '}';
    }


}
