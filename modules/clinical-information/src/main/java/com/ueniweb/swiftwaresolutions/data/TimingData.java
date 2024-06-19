package com.ueniweb.swiftwaresolutions.data;

import lombok.Data;

@Data
public class TimingData {

    private Integer id;

    private String name;

    private Double totalUnits;

    private Integer dayQty;

    private Integer doseDuration;

    private Integer isDiffCalc;

    private Integer diffDays;



    public TimingData(final Integer id, final String name,final  Double totalUnits,final Integer dayQty,final Integer doseDuration,final Integer isDiffCalc,final Integer diffDays) {
        this.id             = id;
        this.name           = name;
        this.totalUnits     = totalUnits;
        this.dayQty         = dayQty;
        this.doseDuration   = doseDuration;
        this.isDiffCalc     = isDiffCalc;
        this.diffDays       = diffDays;
    }

    public static com.ueniweb.swiftwaresolutions.data.TimingData createNewInstance(final Integer id, final String name,final Double totalUnits,final Integer dayQty,final Integer doseDuration,final Integer isDiffCalc,final Integer diffDays) {
        return new com.ueniweb.swiftwaresolutions.data.TimingData(id,name,totalUnits,dayQty,doseDuration,isDiffCalc,diffDays);
    }
}
