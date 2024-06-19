package com.ueniweb.swiftwaresolutions.services;

import com.ueniweb.swiftwaresolutions.data.PatientVisitData;

import java.util.List;

public interface PatientVisitReadPlatformService {

    List<PatientVisitData> fetchGeneralCashSheetDataByPatientId(Long patientId);
}
