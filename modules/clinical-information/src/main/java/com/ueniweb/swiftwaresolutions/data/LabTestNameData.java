package com.ueniweb.swiftwaresolutions.data;

import lombok.Data;

@Data
public class LabTestNameData {

    private String deptCode;

    private Integer id;

    private String name;

    private String code;

    private Double rate;

    private Double charity;

    private String label;



    public LabTestNameData(String deptCode, Integer id,String name,String code,Double rate,Double charity,String label) {

        this.deptCode = deptCode;
        this.id   = id;
        this.name = name;
        this.code= code;
        this.rate     = rate;
        this.charity  = charity;
        this.label    = label;

    }
    public static LabTestNameData createNewInstance(String deptCode,Integer id,String name,String code,Double rate,Double charity,String label) {
        return new LabTestNameData(deptCode,id,name,code,rate,charity,label);
    }
}
