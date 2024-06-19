package com.ueniweb.swiftwaresolutions.data;

import lombok.Data;

@Data
public class PrescriptionData {

    private Long id;

    private String display;

    private String prodName;

    private String quantity;

    private String unit;

    private String timingName;

    private String prescriptionDuration;

    private String configDurationName;

    private String timingUnits;

    private Integer totalNo;

    private Integer isOwn;



    public PrescriptionData(final Long id, final String display, final String prodName, final String quantity, final String unit,
                            final String timingName, final String prescriptionDuration, final String configDurationName,final String timingUnits,Integer totalNo,Integer isOwn) {
        this.id=id;
        this.display = display;
        this.prodName = prodName;
        this.quantity = quantity;
        this.unit = unit;
        this.timingName = timingName;
        this.prescriptionDuration = prescriptionDuration;
        this.configDurationName = configDurationName;
        this.timingUnits        = timingUnits;
        this.totalNo        = totalNo;
        this.isOwn        = isOwn;
    }

    public static PrescriptionData createNewInstance(final Long id,final String display, final String prodName, final String quantity, final String unit,
                                                     final String timingName, final String prescriptionDuration, final String configDurationName,String timingUnits,Integer totalNo,Integer isOwn) {
        return new PrescriptionData(id, display, prodName, quantity, unit, timingName, prescriptionDuration, configDurationName,timingUnits,totalNo,isOwn);
    }
}
