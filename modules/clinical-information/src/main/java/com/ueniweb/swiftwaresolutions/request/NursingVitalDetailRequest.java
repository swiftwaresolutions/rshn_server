package com.ueniweb.swiftwaresolutions.request;

import lombok.Data;

@Data
public class NursingVitalDetailRequest {

    private Long id;
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

    @Override
    public String toString() {
        return "NursingVitalDetailRequest{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", temp='" + temp + '\'' +
                ", pulse='" + pulse + '\'' +
                ", respRate='" + respRate + '\'' +
                ", bp='" + bp + '\'' +
                ", bowels='" + bowels + '\'' +
                ", urine='" + urine + '\'' +
                ", nurseRecords='" + nurseRecords + '\'' +
                ", time='" + time + '\'' +
                ", isValid=" + isValid +
                '}';
    }
}
