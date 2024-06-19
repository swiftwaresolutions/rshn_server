package com.ueniweb.swiftwaresolutions.controllers.patientvisit;

import com.ueniweb.swiftwaresolutions.data.PatientVisitData;
import com.ueniweb.swiftwaresolutions.services.PatientVisitReadPlatformService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/patients")
public class PatientVisitController {

    private final PatientVisitReadPlatformService patientVisitReadPlatformService;

    @GetMapping("getPatientVisitDetails/{patientId}")
    public List<PatientVisitData> getPatientVisitData(@PathVariable(name = "patientId") Long patientId) {
        return this.patientVisitReadPlatformService.fetchGeneralCashSheetDataByPatientId(patientId);
    }
}
