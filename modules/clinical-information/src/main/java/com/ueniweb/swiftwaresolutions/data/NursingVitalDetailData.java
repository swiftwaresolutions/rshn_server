package com.ueniweb.swiftwaresolutions.data;

import lombok.Data;

@Data
public class NursingVitalDetailData {

    private Long id;
    private Long chartId;
    private String date;
    private String temp;
    private String pulse;
    private String respRate;
    private String bp;
    private String bowels;
    private String urine;
    private String nurseRecords;
    private String time;
    private Integer isValid;
}
