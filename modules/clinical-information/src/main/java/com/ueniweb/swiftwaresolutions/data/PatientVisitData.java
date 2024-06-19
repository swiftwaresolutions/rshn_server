package com.ueniweb.swiftwaresolutions.data;

import lombok.Data;

import java.util.Date;


@Data
public class PatientVisitData {

    private String date;

    private String doctorName;

    private String departmentName;

    private Long visitId;

   // private GeneralCaseSheetData generalCaseSheetData;


}
