package com.ueniweb.swiftwaresolutions.data;

import lombok.Data;

@Data
public class LabOrderedData {

    private Integer id;

    private Integer patId;

    private String testName;

    private Integer units;

    private String date;



    public LabOrderedData(Integer id,Integer patId,String testName,Integer units,String date) {

        this.id   = id;
        this.patId = patId;
        this.testName= testName;
        this.units     = units;
        this.date  = date;

    }
    public static LabOrderedData createNewInstance(Integer id,Integer patId,String testName,Integer units,String date) {
        return new LabOrderedData(id,patId,testName,units,date);
    }


}
