package com.ueniweb.swiftwaresolutions.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class CreateOPVitalsRequest {

    private Long patId;

    private Long vstId;

    private String temperature;

    private String pulse;

    private String rr;

    private String bp;

    private String spo2;

    private String height;

    private String weight;

    private String bmi;

    private String datetime;

    @Override
    public String toString() {
        return "CreateOPVitalsRequest{" +
                "patId=" + patId +
                ", vstId=" + vstId +
                ", temperature='" + temperature + '\'' +
                ", pulse='" + pulse + '\'' +
                ", rr='" + rr + '\'' +
                ", bp='" + bp + '\'' +
                ", spo2='" + spo2 + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", bmi=" + bmi +
                ", datetime='" + datetime + '\'' +
                '}';
    }


}
