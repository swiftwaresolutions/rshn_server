package com.ueniweb.swiftwaresolutions.controllers.patient;

import com.ueniweb.swiftwaresolutions.data.PatientData;
import com.ueniweb.swiftwaresolutions.data.PatientIpData;
import com.ueniweb.swiftwaresolutions.data.PatientVisitData;
import com.ueniweb.swiftwaresolutions.data.PrePatientData;
import com.ueniweb.swiftwaresolutions.service.PatientReadPlatformService;
import com.ueniweb.swiftwaresolutions.service.PatientVisitReadPlatformService;
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
    @GetMapping("/fetchPrePatientDetialsByVstId/{vstId}")
    public List<PrePatientData> fetchPrePatientDetialsByVstId(@PathVariable(name = "vstId") Long vstId) {
        return this.patientVisitReadPlatformService.fetchPrePatientDetialsByVstId(vstId);
    }

}
