package com.ueniweb.swiftwaresolutions.service;

import com.ueniweb.swiftwaresolutions.core.services.Page;
import com.ueniweb.swiftwaresolutions.data.Patient;
import com.ueniweb.swiftwaresolutions.data.PatientData;
import com.ueniweb.swiftwaresolutions.data.PatientIpData;

import java.util.List;


public interface PatientReadPlatformService {

    List<PatientData> fetchAllPatientData();

    List<PatientIpData> fetchAllIpPatientData();
}
