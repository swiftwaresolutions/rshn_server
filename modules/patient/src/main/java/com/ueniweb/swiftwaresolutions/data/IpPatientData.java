package com.ueniweb.swiftwaresolutions.data;

import lombok.Data;
@Data
public class IpPatientData {

    private final Long visitId;

    private final String name;

    private final String date;

    private final String time;

    private final String docName;

    private final String departmentName;

    public IpPatientData(Long visitId,String name, String date,String time,String docName,String departmentName) {
        this.visitId = visitId;
        this.name = name;
        this.date = date;
        this.time = time;
        this.docName = docName;
        this.departmentName = departmentName;
    }
    public static IpPatientData newInstance(Long visitId,String name, String date,String time,String docName,String departmentName) {
        return new IpPatientData(visitId, name,  date, time, docName, departmentName);
    }


}
