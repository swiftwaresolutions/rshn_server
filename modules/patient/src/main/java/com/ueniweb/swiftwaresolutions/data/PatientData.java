package com.ueniweb.swiftwaresolutions.data;

import lombok.Data;

@Data
public class PatientData {

    private final String name;

    private final String displayNumber;

    private final String fullName;

    private final String age;

    private final String gender;

    private final String tokenNoDoctor;

    private final String formattedTime;

    private final Long doctorId;

    private final Long patientId;

    private final Long visitId;

    private final boolean isNew;

    private final int casesheetStatus;

    private final int labStatus;

    private final int procStatus;

    private final int prescriptionStatus;

    private final int vitalStatus;

    private final String departmentName;


    public PatientData(String name, String displayNumber, String fullName, String age,
                       String gender, String tokenNoDoctor, String formattedTime, Long doctorId,
                       Long patientId, Long visitId, boolean isNew, int casesheetStatus, int labStatus,
                       int procStatus, int prescriptionStatus, String departmentName,int vitalStatus) {
        this.name = name;
        this.displayNumber = displayNumber;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.tokenNoDoctor = tokenNoDoctor;
        this.formattedTime = formattedTime;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.visitId = visitId;
        this.isNew = isNew;
        this.casesheetStatus = casesheetStatus;
        this.labStatus = labStatus;
        this.procStatus = procStatus;
        this.prescriptionStatus = prescriptionStatus;
        this.vitalStatus = vitalStatus;
        this.departmentName = departmentName;
    }

    public static PatientData newInstance(String name, String displayNumber, String fullName,
                                          String age, String gender, String tokenNoDoctor, String formattedTime,
                                          Long doctorId, Long patientId, Long visitId, boolean isNew,
                                          int casesheetStatus, int labStatus, int procStatus,
                                          int prescriptionStatus, String departmentName,int vitalStatus) {
        return new PatientData(name, displayNumber, fullName, age, gender, tokenNoDoctor, formattedTime,
                doctorId, patientId, visitId, isNew, casesheetStatus, labStatus, procStatus, prescriptionStatus, departmentName,vitalStatus);
    }

}
