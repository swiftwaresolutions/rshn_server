package com.ueniweb.swiftwaresolutions.service;

import com.ueniweb.swiftwaresolutions.data.IpPatientData;
import com.ueniweb.swiftwaresolutions.data.PatientData;
import com.ueniweb.swiftwaresolutions.data.PatientVisitData;
import com.ueniweb.swiftwaresolutions.data.PrePatientData;

import java.util.List;

public interface PatientVisitReadPlatformService {

    List<PatientVisitData> fetchPatientVisitDetailsByPatientId(Long patientId);
    List<PatientVisitData> fetchPatientVisitDetailsByPatientDisplayNumber(String patientDisplayNumber);
    List <PrePatientData>fetchPrePatientDetialsByVstId(Long vstId);
    List <IpPatientData>fetchIpVisitsByDisplayNo(String display);
    List <PatientData> fetchPatientPrevVisitByPatientDisplayNumber(String displayNumber);

}
