package com.ueniweb.swiftwaresolutions.data;

import lombok.Data;

@Data
public class PrevPrescDetailsData {

    private String displayNo;

    private Long genId;

    private String genName;

    private Long id;

    private String medName;

    private Long formType;

    private String unit;

    private Long unitId;

    private Integer medStrength;

    private Integer medQuantity;

    private Integer stock;

    private String timing;

    private Long timingId;

    private Double quantity;

    private Double duration;

    private String period;

    private Long periodId;

    private Double no;

    private String timingUnit;

    private Double mrpPrice;

    private Integer own;

    private String date;

    private Integer isFromSummary;

    private Integer isBilled;

    private Integer isManual;

    public PrevPrescDetailsData(String displayNo, Long genId, String genName, Long id, String medName, Long formType, String unit, Long unitId, Integer medStrength, Integer medQuantity, Integer stock, String timing, Long timingId, Double quantity, Double duration, String period, Long periodId, Double no, String timingUnit, Double mrpPrice, Integer own, String date, Integer isFromSummary, Integer isBilled, Integer isManual) {

        this.displayNo = displayNo;
        this.genId = genId;
        this.genName = genName;
        this.id = id;
        this.medName = medName;
        this.formType = formType;
        this.unit = unit;
        this.unitId = unitId;
        this.medStrength = medStrength;
        this.medQuantity = medQuantity;
        this.stock = stock;
        this.timing = timing;
        this.timingId = timingId;
        this.quantity = quantity;
        this.duration = duration;
        this.period = period;
        this.periodId = periodId;
        this.no = no;
        this.timingUnit = timingUnit;
        this.mrpPrice = mrpPrice;
        this.own = own;
        this.date = date;
        this.isFromSummary = isFromSummary;
        this.isBilled = isBilled;
        this.isManual = isManual;
    }

    public static PrevPrescDetailsData createNewInstance(String displayNo, Long genId, String genName, Long id, String medName, Long formType, String unit, Long unitId, Integer medStrength, Integer medQuantity, Integer stock, String timing, Long timingId, Double quantity, Double duration, String period, Long periodId, Double no, String timingUnit, Double mrpPrice, Integer own, String date, Integer isFromSummary, Integer isBilled, Integer isManual) {
        return new PrevPrescDetailsData(displayNo, genId, genName, id, medName, formType, unit, unitId, medStrength, medQuantity, stock, timing, timingId, quantity, duration, period, periodId, no, timingUnit, mrpPrice, own, date,isFromSummary,isBilled,isManual);
    }
}
