package com.ueniweb.swiftwaresolutions.data;

import lombok.Data;

@Data
public class InvestigationOrderedData {

    private Integer id;

    private Integer patId;

    private String procName;

    private Integer units;

    private String date;

    public InvestigationOrderedData(Integer id,Integer patId,String procName,Integer units,String date) {

        this.id   = id;
        this.patId = patId;
        this.procName= procName;
        this.units     = units;
        this.date  = date;

    }
    public static InvestigationOrderedData createNewInstance(Integer id,Integer patId,String procName,Integer units,String date) {
        return new InvestigationOrderedData(id,patId,procName,units,date);
    }

}
