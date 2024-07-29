package com.ueniweb.swiftwaresolutions.service;

import com.ueniweb.swiftwaresolutions.data.PatientVisitData;

import java.util.List;

public interface PatientVisitReadPlatformService {

    List<PatientVisitData> fetchPatientVisitDetailsByPatientId(Long patientId);
    List<PatientVisitData> fetchPatientVisitDetailsByPatientDisplayNumber(String patientDisplayNumber);

}
