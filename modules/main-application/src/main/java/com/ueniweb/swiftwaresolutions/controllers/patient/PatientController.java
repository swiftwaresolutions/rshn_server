package com.ueniweb.swiftwaresolutions.controllers.patient;

import com.ueniweb.swiftwaresolutions.core.services.Page;
import com.ueniweb.swiftwaresolutions.data.*;
import com.ueniweb.swiftwaresolutions.domain.Department;
import com.ueniweb.swiftwaresolutions.domain.Ward;
import com.ueniweb.swiftwaresolutions.service.DepartmentReadPlatformService;
import com.ueniweb.swiftwaresolutions.service.PatientReadPlatformService;
import com.ueniweb.swiftwaresolutions.service.PatientVisitReadPlatformService;
import com.ueniweb.swiftwaresolutions.service.WardReadPlatformService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patients")
@Slf4j
public class PatientController {

    private final PatientReadPlatformService patientReadPlatformService;

    private final PatientVisitReadPlatformService patientVisitReadPlatformService;

    @GetMapping("/outPatients")
    public List<PatientData> getAllPatientDataList(@RequestParam(name = "page", required = false, defaultValue = "500") int pageSize,
                                                   @RequestParam(name = "pageSize", required = false, defaultValue = "1") int page) {
        return this.patientReadPlatformService.fetchAllPatientData();
    }

    //    @GetMapping("/templates")
//    public PatientTemplateData getPatientTemplates() {
//        List<Department> departmentList = this.departmentReadPlatformService.fetchAllDepartments();
//        List<Ward> wardList = this.wardReadPlatformService.fetchAllWards();
//        PatientTemplateData patientTemplateData = new PatientTemplateData();
//        patientTemplateData.setDepartmentList(departmentList);
//        patientTemplateData.setWardList(wardList);
//        return patientTemplateData;
//    }
    @GetMapping("getPatientVisitDetails/{patientId}")
    public List<PatientVisitData> getPatientVisitData(@PathVariable(name = "patientId") Long patientId) {
        return this.patientVisitReadPlatformService.fetchPatientVisitDetailsByPatientId(patientId);
    }
    @GetMapping("/inPatients")
    public List<PatientIpData> fetchAllIpPatientData(){
        return this.patientReadPlatformService.fetchAllIpPatientData();
    }
    @GetMapping("getPatientVisitDetailsByPatDisplay/{patientDisplayNumber}")
    public List<PatientVisitData> getPatientVisitDataByPatientDisplayNumber(@PathVariable(name = "patientDisplayNumber") String patientDisplayNumber) {
        return this.patientVisitReadPlatformService.fetchPatientVisitDetailsByPatientDisplayNumber(patientDisplayNumber);
    }
}
