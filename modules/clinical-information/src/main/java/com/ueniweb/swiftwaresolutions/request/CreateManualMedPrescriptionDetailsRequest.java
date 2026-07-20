package com.ueniweb.swiftwaresolutions.request;

import lombok.Data;

@Data
public class CreateManualMedPrescriptionDetailsRequest {

    private Long prescription_id;

    private String med_name;

    private Double quantity;

    private Long unit;

    private Long timing;

    private Long is_own;

    private Long is_cancelled;

    private String notes;

    @Override
    public String toString() {
        return "CreateManualMedPrescriptionDetailsRequest{" +
                "prescription_id=" + prescription_id +
                ", med_name='" + med_name + '\'' +
                ", quantity=" + quantity +
                ", unit=" + unit +
                ", timing=" + timing +
                ", is_own=" + is_own +
                ", is_cancelled=" + is_cancelled +
                ", notes='" + notes + '\'' +
                '}';
    }
}