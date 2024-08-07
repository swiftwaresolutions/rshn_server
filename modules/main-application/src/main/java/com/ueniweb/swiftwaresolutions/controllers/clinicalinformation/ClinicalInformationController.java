package com.ueniweb.swiftwaresolutions.controllers.clinicalinformation;


import com.ueniweb.swiftwaresolutions.core.exception.HimsApplicationContextException;
import com.ueniweb.swiftwaresolutions.core.response.Response;
import com.ueniweb.swiftwaresolutions.data.*;
import com.ueniweb.swiftwaresolutions.request.*;
import com.ueniweb.swiftwaresolutions.security.PlatformSecurityContext;

import com.ueniweb.swiftwaresolutions.services.ClinicalInfoReadPlatformService;
import com.ueniweb.swiftwaresolutions.services.ClinicalInfoWritePlatformService;
import com.ueniweb.swiftwaresolutions.servers.ProductDetailsReadPlatformService;
import com.ueniweb.swiftwaresolutions.services.UserReadPlatformService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
public class ClinicalInformationController {

    private final ClinicalInfoWritePlatformService clinicalInfoWritePlatformService;

    private final PlatformSecurityContext platformSecurityContext;

    private final ProductDetailsReadPlatformService productDetailsReadPlatformService;

    private final ClinicalInfoReadPlatformService clinicalInfoReadPlatformService;

    private final UserReadPlatformService userReadPlatformService;

    @PostMapping("/saveGeneralCaseSheet")
    public Response saveGeneralCaseSheet(@RequestBody GeneralCaseSheetRequest generalCaseSheetRequest) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        return this.clinicalInfoWritePlatformService.saveGeneralCaseSheet(generalCaseSheetRequest);
    }

    @PostMapping("/savePrescription")
    public List<PrescriptionData>  savePrescription(@RequestBody CreatePrescriptionRequest createPrescriptionRequest) {
//        final AppUser appUser = this.platformSecurityContext.authenticateUser();
//        if (appUser.getUser().getIsDoctor() != 1) {
//            throw new HimsApplicationContextException("Access Only For Doctors !");
//        }
        return this.clinicalInfoWritePlatformService.savePrescription(createPrescriptionRequest);
    }

    @PutMapping("/updateGeneralCaseSheet/{id}/{caseSheetType}")
    public void updatePrescription(@PathVariable(name = "id") Long id,
                                   @PathVariable(name = "caseSheetType") Integer caseSheetType,
                                   @RequestBody GeneralCaseSheetRequest generalCaseSheetRequest) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        this.clinicalInfoWritePlatformService.updateGeneralCaseSheet(id,generalCaseSheetRequest,caseSheetType);
    }

    @PostMapping("/saveInvestigationOrder")
    public void saveInvestigationOrder(@RequestBody List<CreateInvestigationOrderRequest> createInvestigationOrderRequest) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        this.clinicalInfoWritePlatformService.saveInvestigationOrder(createInvestigationOrderRequest);
    }
    @PutMapping("/updatePrescription/{id}")
    public List<PrescriptionData> updatePrescription(@PathVariable(name = "id") Long id, @RequestBody UpdatePrescriptionRequest prescriptionRequest) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        return this.clinicalInfoWritePlatformService.updatePrescription(id,prescriptionRequest);
    }

    @PostMapping("/saveLabOrder")
    public void saveLabOrder(@RequestBody List<CreateLabOrderRequest> createLabOrderRequests) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        this.clinicalInfoWritePlatformService.saveLabOrder(createLabOrderRequests);
    }

    @GetMapping("/fetchPrevPrescriptionDetails/{patId}/{storeId}")
    public List<PrevPrescriptionDetailsData> fetchPatientLastPrescription(@PathVariable(name = "patId") Long patId,
                                                                          @PathVariable(name = "storeId") Integer storeId) {
        return this.clinicalInfoReadPlatformService.fetchPrevPrescriptionDetails(patId,storeId);
    }

    @GetMapping("/fetchPrescriptionDetailsByVstId/{vstId}/{isFromSummary}")
    public List<PrevPrescriptionDetailsData> fetchPrescriptionDetailsByVstId(@PathVariable(name = "vstId") Long vstId,
                                                                             @PathVariable(name= "isFromSummary") Integer isFromSummary) {
        return this.clinicalInfoReadPlatformService.fetchPrescriptionDetailsByVstId(vstId,isFromSummary);
    }

    @GetMapping("/fetchPatientLastPrescriptionDetails/{patId}/{storeId}")
    public List<PrevPrescriptionDetailsData> fetchPrescriptionDetails(@PathVariable(name = "patId") Long patId,
                                                                      @PathVariable(name = "storeId") Integer storeId) {
        return this.clinicalInfoReadPlatformService.fetchPatientLastPrescription(patId,storeId);
    }

    @GetMapping("/fetchComplaintDetails/{complaintName}")
    public List<ComplaintData> fetchComplaintDetails(@PathVariable(name = "complaintName") String complaintName){
        return this.clinicalInfoReadPlatformService.fetchComplaintDetails(complaintName);
    }

    @GetMapping("/fetchDiagnosisDetails/{diagName}")
    public List<DiagnosisData> fetchDiagnosisDetails(@PathVariable(name="diagName")String diagName){
        return this.clinicalInfoReadPlatformService.fetchDiagnosisDetails(diagName);
    }

    @GetMapping("/fetchGenericDetails/{genName}")
    public List<GenericData> fetchGenericDetails(@PathVariable(name = "genName") String genName){
        return this.clinicalInfoReadPlatformService.fetchGenericDetails(genName);
    }

    @GetMapping("/fetchTimingDetails")
    public List<TimingData> fetchTimingDetails(){
        return this.clinicalInfoReadPlatformService.fetchTimingDetails();
    }

    @GetMapping("/fetchDurationDetails")
    public List<DurationData> fetchDurationDetails(){
        return this.clinicalInfoReadPlatformService.fetchDurationDetails();
    }

    @GetMapping("/fetchConsultant/{consultantName}")
    public List<ConsultantData> fetchConsultant(@PathVariable(name = "consultantName") String consultantName){
        return this.clinicalInfoReadPlatformService.fetchConsultant(consultantName);
    }

    @GetMapping("/fetchUnitDetails")
    public List<UnitData> fetchUnitDetails() {
        return this.clinicalInfoReadPlatformService.fetchUnitDetails();
    }

    @DeleteMapping("/deleteLabOrderByLabId/{labId}")
    public Response deleteLabOrderByLabId(@PathVariable(name = "labId") Long labId) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access  Only For Doctors !");
        }
        return this.clinicalInfoWritePlatformService.deleteLabOrderByLabId(labId);
    }

    @DeleteMapping("/deleteLabOrderByPatientId/{patientId}")
    public Response deleteLabOrderByPatientId(@PathVariable(name = "patientId") Long patientId) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access  Only For Doctors !");
        }
        return this.clinicalInfoWritePlatformService.deleteLabOrderByPatientId(patientId);
    }

    @DeleteMapping("/deleteInvestigationOrderById/{id}")
    public Response deleteInvestigationOrderByOrderId(@PathVariable(name = "id") Long id) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        return this.clinicalInfoWritePlatformService.deleteInvestigationOrderById(id);
    }

    @DeleteMapping("/deleteInvestigationOrderByPatientId/{patientId}")
    public Response deleteInvestigationOrderByPatientId(@PathVariable(name = "patientId") Long patientId) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access  Only For Doctors !");
        }
        return this.clinicalInfoWritePlatformService.deleteInvestigationOrderByPatientId(patientId);
    }

    @GetMapping("/fetchLabDepartment")
    public List<LabDepartmentData> fetchLabDepartment() {
        return this.clinicalInfoReadPlatformService.fetchLabDepartment();
    }

    @GetMapping("/fetchInvestigationDepartment")
    public List<InvDepartmentData> fetchInvestigationDepartment() {
        return this.clinicalInfoReadPlatformService.fetchInvestigationDepartment();
    }

    @GetMapping("fetchLabResultsByVisitId/{visitId}")
    public List<LabResultData> fetchLabResultsDetailsByVisitId(@PathVariable(name = "visitId") Long visitId) {
        return this.clinicalInfoReadPlatformService.fetchLabResultDetailsByVisitId(visitId);
    }

    @GetMapping("fetchInvestigationNamesByGroupId/{groupId}")
    public List<InvestigationData> fetchInvestigationDataByGroupId(@PathVariable(name = "groupId") Long groupId) {
        return this.clinicalInfoReadPlatformService.fetchInvestigationDetailsByGroupId(groupId);
    }

    @PostMapping("saveCaseSheetTemplate")
    public Response saveCaseSheetTemplate(@RequestBody CreateCaseSheetTemplateRequest createCaseSheetTemplateRequest) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access  Only For Doctors !");
        }
        return this.clinicalInfoWritePlatformService.saveCaseSheetTemplate(createCaseSheetTemplateRequest);
    }

    @GetMapping("fetchLabTestNameByGroupId/{deptId}")
    public List<LabTestNameData> fetchLabTestNameByGroupId(@PathVariable(name = "deptId") Long deptId) {
        return this.clinicalInfoReadPlatformService.fetchLabTestNameByGroupId(deptId);
    }

    @GetMapping("/fetchMedicineNames/{medName}/{storeId}")
    public List<MedicineNameData> fetchMedicineNames(@PathVariable(name = "medName") String medName,
                                                    @PathVariable(name = "storeId") Integer storeId){
        return this.productDetailsReadPlatformService.fetchMedicineNames(medName,storeId);
    }

    @GetMapping("/fetchCaseSheetTemplates/{casesheetType}/{templateFieldId}")
    public List<CaseSheetTemplateData> fetchCaseSheetTemplates(@PathVariable(name = "casesheetType") Integer casesheetType,
                                                               @PathVariable(name = "templateFieldId") Integer templateFieldId) {
        return this.productDetailsReadPlatformService.fetchCaseSheetTemplates(casesheetType,templateFieldId);
    }

    @GetMapping("/fetchStoreWiseAvailableStock/{prodsId}")
    public List<StoreWiseAvailableStockData> fetchStoreWiseAvailableStock(@PathVariable(name = "prodsId") Integer prodsId) {
        return this.productDetailsReadPlatformService.fetchStoreWiseAvailableStock(prodsId);
    }

    @GetMapping("/fetchPrescriptionTemplates/{templateType}")
    public List<TemplateData> fetchPrescriptionTemplates(@PathVariable(name = "templateType") Long templateType) {
        return this.clinicalInfoReadPlatformService.fetchPrescriptionTemplates(templateType);
    }

    @GetMapping("/fetchPrescriptionTemplateDetailsById/{id}/{storeId}")
    public List<PrescTemplateDetailsData> fetchPrescriptionTemplateDetailsById(@PathVariable(name = "id") Long id,
                                                                               @PathVariable(name = "storeId") Integer storeId) {
        return this.clinicalInfoReadPlatformService.fetchPrescriptionTemplateDetailsById(id,storeId);
    }

    @GetMapping("/fetchMedicineUnitsById/{prodsId}")
    public List<ProdsUnitData> fetchMedicineUnitsById(@PathVariable(name = "prodsId") Integer prodsId) {
        return this.productDetailsReadPlatformService.fetchMedicineUnitsById(prodsId);
    }

    @GetMapping("/fetchEqualentDrugsByGenericId/{genericId}")
    public List<EqualantDrugsData> fetchEqualentDrugsByGenericId(@PathVariable(name = "genericId") Integer genericId) {
        return this.productDetailsReadPlatformService.fetchEqualentDrugsByGenericId(genericId);
    }

    @PostMapping("saveOrderDiscount")
    public Response saveOrderDiscount(@RequestBody CreateOrderDiscountRequest createOrderDiscountRequest) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access  Only For Doctors !");
        }
        return this.clinicalInfoWritePlatformService.saveOrderDiscount(createOrderDiscountRequest);
    }
    @GetMapping("/fetchGeneralCaseSheetByVstId/{vstId}")
        public Map<String, Object> fetchGeneralCaseSheetByVstId(@PathVariable(name = "vstId") Long vstId) {
        return this.clinicalInfoReadPlatformService.fetchGeneralCaseSheetByVstId(vstId);
    }

    @PostMapping("/savePrescriptionTemplate")
    public Response savePrescriptionTemplate(@RequestBody CreatePrescTemplateRequest createPrescTemplateRequest) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access  Only For Doctors !");
        }
      return this.clinicalInfoWritePlatformService.savePrescriptionTemplate(createPrescTemplateRequest);
    }

    @GetMapping("/fetchLabOrderedDetails/{patId}")
    public List<LabOrderedData> fetchLabOrderedDetails(@PathVariable(name = "patId") Long patId) {
        return this.clinicalInfoReadPlatformService.fetchLabOrderedDetails(patId);
    }

    @GetMapping("/fetchInvestigationOrderedDetails/{patId}")
    public List<InvestigationOrderedData> fetchInvestigationOrderedDetails(@PathVariable(name = "patId") Long patId) {
        return this.clinicalInfoReadPlatformService.fetchInvestigationOrderedDetails(patId);
    }
    @GetMapping("/fetchOrderDiscountAmount/{patId}")
    public List<DiscountData> fetchOrderDiscountAmount(@PathVariable(name = "patId") Long patId) {
        return this.clinicalInfoReadPlatformService.fetchOrderDiscountAmount(patId);
    }
    @DeleteMapping("/deleteOrderDiscById/{patId}")
    public Response deleteOrderDiscByPatId(@PathVariable(name = "patId") Long patId) {
        return this.clinicalInfoWritePlatformService.deleteOrderDiscByPatId(patId);
    }

    @PostMapping("/saveLabInvOrderTemplate")
    public void saveProcOrderTemplate(@RequestBody CreateOrderTemplateRequest createOrderTemplateRequest) {
        this.clinicalInfoWritePlatformService.saveProcOrderTemplate(createOrderTemplateRequest);
    }

    @GetMapping("/fetchOrderTemplates")
    public List<TemplateData> fetchOrderTemplates() {
        return this.clinicalInfoReadPlatformService.fetchOrderTemplates();
    }

    @GetMapping("fetchLabOrderTemplatesById/{templateId}")
    public List<LabTestNameData> fetchLabOrderTemplatesById(@PathVariable(name = "templateId") Long templateId) {
        return this.clinicalInfoReadPlatformService.fetchLabOrderTemplatesById(templateId);
    }

    @GetMapping("fetchInvOrderTemplatesById/{templateId}")
    public List<InvestigationData> fetchInvOrderTemplatesById(@PathVariable(name = "templateId") Long templateId) {
        //AppUser appUser = this.platformSecurityContext.authenticateUser();
        //Long userId = appUser.getUser().getId();
        //UserRightsData userRightsData = this.userReadPlatformService.CheckUserRight(userId,6,11,182);
//        if (userRightsData == null) {
//            throw new HimsApplicationContextException("Access Denied for this user");
//        }
        return this.clinicalInfoReadPlatformService.fetchInvOrderTemplatesById(templateId);
    }


    @PostMapping("/savePediatricCaseSheet")
    public  Response savePediatricCaseSheet(@RequestBody CreatePeadiatricCaseSheetRequest createPeadiatricCaseSheetRequest) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        return this.clinicalInfoWritePlatformService.savePediatricCaseSheet(createPeadiatricCaseSheetRequest);
    }
    @PostMapping("/saveDischargeSummary")
    public Response saveDischargeSummary(@RequestBody CreateDischargeSummaryRequest createDischargeSummaryRequest) {

//    final AppUser appUser = this.platformSecurityContext.authenticateUser();
    //if (appUser.getUser().getIsDoctor() != 1) {
        //throw new HimsApplicationContextException("Access Only For Doctors !");
    //}

    return this.clinicalInfoWritePlatformService.saveDischargeSummary(createDischargeSummaryRequest);
}

    @PutMapping("/updateDischargeSummary/{id}")
    public void updateDischargeSummary(@PathVariable(name = "id") Long id, @RequestBody CreateDischargeSummaryRequest createDischargeSummaryRequest) {
//        final AppUser appUser = this.platformSecurityContext.authenticateUser();
//        if (appUser.getUser().getIsDoctor() != 1) {
//            throw new HimsApplicationContextException("Access Only For Doctors !");
//        }
        this.clinicalInfoWritePlatformService.updateDischargeSummary(id,createDischargeSummaryRequest);
    }
    @PutMapping("/updatePediatricCaseSheet/{id}/{caseSheetType}")
    public void updatePediatricCaseSheet(@PathVariable(name = "id") Long id,
                                         @RequestBody CreatePeadiatricCaseSheetRequest createPeadiatricCaseSheetRequest,
                                         @PathVariable(name = "caseSheetType") Integer caseSheetType) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        this.clinicalInfoWritePlatformService.updatePediatricCaseSheet(id,createPeadiatricCaseSheetRequest,caseSheetType);
    }

    @GetMapping("/fetchPediatricCaseSheetByVstId/{vstId}")
    public List<PediatricCaseSheetData> fetchPediatricCaseSheetByVstId(@PathVariable(name = "vstId") Long vstId) {
        return this.clinicalInfoReadPlatformService.fetchPediatricCaseSheetByVstId(vstId);
    }

    @PostMapping("/saveNeonateCaseSheet")
    public  Response saveNeonateCaseSheet(@RequestBody CreateNeoNateCaseSheetRequest createNeoNateCaseSheetRequest) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        return this.clinicalInfoWritePlatformService.saveNeonateCaseSheet(createNeoNateCaseSheetRequest);
    }

    @PutMapping("/updateNeonateCaseSheet/{id}/{caseSheetType}")
    public void updateNeonateCaseSheet(@PathVariable(name = "id") Long id, @RequestBody CreateNeoNateCaseSheetRequest createNeoNateCaseSheetRequest,
                                       @PathVariable(name = "caseSheetType") Integer caseSheetType) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        this.clinicalInfoWritePlatformService.updateNeonateCaseSheet(id,createNeoNateCaseSheetRequest,caseSheetType);
    }

    @GetMapping("/fetchNeonateCaseSheetByVstId/{vstId}")
    public List<NeonateCaseSheetData> fetchNeonateCaseSheetByVstId(@PathVariable(name = "vstId") Long vstId) {
        return this.clinicalInfoReadPlatformService.fetchNeonateCaseSheetByVstId(vstId);
    }
    @PostMapping("/saveAnthropometry")
    public void saveAnthropometry(@RequestBody List<CreateAnthropometryRequest> createAnthropometryRequests) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        this.clinicalInfoWritePlatformService.saveAnthropometry(createAnthropometryRequests);
    }
    @PutMapping("/updateAnthropometry/{vstId}")
    public void updateAnthropometry(@PathVariable(name = "vstId") Long vstId, @RequestBody List<CreateAnthropometryRequest> createAnthropometryRequest) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        this.clinicalInfoWritePlatformService.updateAnthropometry(vstId,createAnthropometryRequest);
    }

    @GetMapping("/fetchAnthropometryByVstId/{vstId}")
    public List<AnthropometryData> fetchAnthropometryByVstId(@PathVariable(name = "vstId") Long vstId) {
        return this.clinicalInfoReadPlatformService.fetchAnthropometryByVstId(vstId);
    }

    @PostMapping("saveOpVitals")
    public Response saveOpVitals(@RequestBody CreateOPVitalsRequest createOPVitalsRequest) {
        return this.clinicalInfoWritePlatformService.saveOpVitals(createOPVitalsRequest);
    }

    @PutMapping("/updateOPVitals/{id}")
    public void updatePediatricCaseSheet(@PathVariable(name = "id") Long id, @RequestBody CreateOPVitalsRequest createOPVitalsRequest) {
        this.clinicalInfoWritePlatformService.updateOPVitals(id,createOPVitalsRequest);
    }

    @GetMapping("/fetchOpVitalsByVstId/{vstId}")
    public List<OPVitalsData> fetchOpVitalsByVstId(@PathVariable(name = "vstId") Long vstId) {
        return this.clinicalInfoReadPlatformService.fetchOpVitalsByVstId(vstId);
    }

    @PostMapping("saveSummaryTemplates")
    public Response saveSummaryTemplates(@RequestBody CreateSummaryTemplateRequest createSummaryTemplateRequest) {
        return this.clinicalInfoWritePlatformService.saveSummaryTemplates(createSummaryTemplateRequest);
    }

    @GetMapping("/fetchSummaryTemplate/{fieldId}")
    public List<SummaryTemplateData> fetchSummaryTemplate(@PathVariable(name = "fieldId") Long fieldId) {
        return this.clinicalInfoReadPlatformService.fetchSummaryTemplate(fieldId);
    }

    @GetMapping("/fetchDentalCaseSheetByVstId/{vstId}")
    public Map<String, Object> fetchDentalCaseSheetByVstId(@PathVariable(name = "vstId") Long vstId) {
        return this.clinicalInfoReadPlatformService.fetchDentalCaseSheetByVstId(vstId);
    }

    @PostMapping("saveDentalCaseSheet")
    public Response saveDental(@RequestBody CreateDentalCasesheetRequest createDentalCasesheetRequest) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        return this.clinicalInfoWritePlatformService.saveDentalCaseSheet(createDentalCasesheetRequest);
    }

    @PutMapping("/updateDentalCaseSheet/{id}")
    public void updateDentalCaseSheet(@PathVariable(name = "id") Long id, @RequestBody CreateDentalCasesheetRequest createdentalCaseSheetRequest) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        this.clinicalInfoWritePlatformService.updateDentalCaseSheet(id, createdentalCaseSheetRequest);
    }

    @PostMapping("/saveAntenatalCaseSheet")
    public Response saveAntenatelCaseSheet(@RequestBody CreateAntenatalCaseSheetRequest createAntenatalCaseSheetRequest) {
         final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        return this.clinicalInfoWritePlatformService.saveAntenatalCaseSheet(createAntenatalCaseSheetRequest);
    }

    @GetMapping("/fetchAntenatalCaseSheetByVstId/{vstId}")
    public List<AntenatelCaseSheetData> fetchAntenatalCaseSheetByVstId(@PathVariable(name = "vstId") Long vstId) {
        return this.clinicalInfoReadPlatformService.fetchAntenatalCaseSheetByVstId(vstId);
    }
    @PutMapping("/updateAntenatalCaseSheet/{id}")
    public void updateAntenatalCaseSheet(@PathVariable(name = "id") Long id, @RequestBody CreateAntenatalCaseSheetRequest createAntenatalCaseSheetRequest) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        this.clinicalInfoWritePlatformService.updateAntenatalCaseSheet(id,createAntenatalCaseSheetRequest);
    }

    @GetMapping("/fetchDischargeSummaryByVstId/{vstId}")
    public Map<String, Object> fetchDischargeSummaryByVstId(@PathVariable(name = "vstId") Long vstId) {
        return this.clinicalInfoReadPlatformService.fetchDischargeSummaryByVstId(vstId);
    }

    @GetMapping("/fetchPatientImageDetails/{patId}")
    public List<PatientUploadImageData> fetchXrayImageDetails(@PathVariable(name = "patId") String patId) {
        return this.clinicalInfoReadPlatformService.fetchPatientImageDetails(patId);
    }

    @Value("${xrayImage.folder}")  private String xrayImageFolderLocation;
    @GetMapping("/fetchPatientImage/{imageName}")
    public ResponseEntity<byte[]> fetchAFile(@PathVariable(name = "imageName") String imageName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/octet-stream");
        try {
            File file = new File(xrayImageFolderLocation+imageName);
            if (file.isFile()) {
                byte[]  fileContent = Files.readAllBytes(file.toPath());
                return ResponseEntity.ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(fileContent)
                        ;
            }else {
                return ResponseEntity.notFound().build();
            }
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/saveSurgeryCaseSheet")
    public Response saveSurgeryCaseSheet(@RequestBody CreateSurgeryCaseSheetRequest createSurgeryCaseSheetRequest) {
        //  final AppUser appUser = this.platformSecurityContext.authenticateUser();
        //   if (appUser.getUser().getIsDoctor() != 1) {
        //       throw new HimsApplicationContextException("Access Only For Doctors !");
        //   }
        return this.clinicalInfoWritePlatformService.saveSurgeryCaseSheet(createSurgeryCaseSheetRequest);
    }

    @GetMapping("/fetchSurgeryCaseSheetByVstId/{visitId}")
    public List<SurgeryCaseSheetData> fetchSurgeryCaseSheetByVstId(@PathVariable(name = "visitId") Long visitId) {
        return this.clinicalInfoReadPlatformService.fetchSurgeryCaseSheetByVstId(visitId);

    }

    @PutMapping("/updateSurgeryCaseSheet/{id}")
    public void updateSurgerylCaseSheet(@PathVariable(name = "id") Long id, @RequestBody CreateSurgeryCaseSheetRequest createSurgeryCaseSheetRequest) {
//        final AppUser appUser = this.platformSecurityContext.authenticateUser();
//        if (appUser.getUser().getIsDoctor() != 1) {
//            throw new HimsApplicationContextException("Access Only For Doctors !");
//        }
        this.clinicalInfoWritePlatformService.updatSurgeryCaseSheet(id, createSurgeryCaseSheetRequest);
    }
    @PostMapping("saveNursingIo")
    public Response saveNursingIo(@RequestBody CreateNursingIoReuest createNursingIoReuest) {
        return this.clinicalInfoWritePlatformService.saveNursingIoSheet(createNursingIoReuest);
    }
    @GetMapping("/fetchNursingIOByVstId/{vstId}")
    public List<NursingIoData> fetchNursingIoByVstId(@PathVariable(name = "vstId") Long vstId) {
        return this.clinicalInfoReadPlatformService.fetchNursingIoByVstId(vstId);
    }
    @PutMapping("/updateNursingIO/{id}")
    public void updateNursingIoSheet(@PathVariable(name = "id") Long id, @RequestBody CreateNursingIoReuest createNursingIoReuest) {
        this.clinicalInfoWritePlatformService.updateNursingIoSheet(id,createNursingIoReuest);
    }
    @PostMapping("saveSurgeryChecklist")
    public Response saveSurgeryChecklist(@RequestBody CreateSurgeryChecklistReuest createSurgeryChecklistReuest) {
        return this.clinicalInfoWritePlatformService.saveSurgeryChecklist(createSurgeryChecklistReuest);
    }
    @GetMapping("/fetchSurgeryChecklistByVatId/{vstId}")
    public List<SurgeryCheckListData> fetchSurgeryChecklistByVatId(@PathVariable(name = "vstId") Long vstId) {
        return this.clinicalInfoReadPlatformService.fetchSurgeryChecklistByVatId(vstId);
    }
    @PutMapping("/updateSurgeryChecklist/{id}")
    public void updateSurgeryChecklist(@PathVariable(name = "id") Long id, @RequestBody CreateSurgeryChecklistReuest createSurgeryChecklistReuest) {
        this.clinicalInfoWritePlatformService.updateSurgeryChecklist(id,createSurgeryChecklistReuest);
    }

    @PostMapping("/saveIpProcedureCaseSheet")
    public Response saveIpProcedureCaseSheet (@RequestBody CreateIpProcedureCaseSheetRequest createIpProcedureCaseSheetRequest){
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only for Doctors !");
        }
        return this.clinicalInfoWritePlatformService.saveIpProcedureCaseSheet(createIpProcedureCaseSheetRequest);
    }


    @GetMapping("/fetchIpProcedureCaseSheetByVstId/{vstId}")
    public List<IpProcedureCaseSheetData> fetchIpProcedureCaseSheetByVstId(@PathVariable(name = "vstId") Long vstId){
        return this.clinicalInfoReadPlatformService.fetchIpProcedureCaseSheetByVstId(vstId);
    }

    @PutMapping("/updateIpProcedureCaseSheet/{id}")
    public void updateIpProcedureCaseSheet(@PathVariable(name = "id") Long id, @RequestBody CreateIpProcedureCaseSheetRequest createIpProcedureCaseSheetRequest) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        this.clinicalInfoWritePlatformService.updateIpProcedureCaseSheet(id, createIpProcedureCaseSheetRequest);
    }

    @PostMapping("/saveAldreteScoreChart")
    public Response saveAldreteScoreChart(@RequestBody CreateAldreteScoreChartReuest createAldreteScoreChartReuest) {
        return this.clinicalInfoWritePlatformService.saveAldreteScoreChart(createAldreteScoreChartReuest);
    }
    @GetMapping("/fetchAldreteScoreChartByVatId/{vstId}")
    public List<AldreteScoreChartData> fetchAldreteScoreChartByVstId(@PathVariable(name = "vstId") Long vstId) {
        return this.clinicalInfoReadPlatformService.fetchAldreteScoreChartByVstId(vstId);
    }
    @PutMapping("/updateAldreteScoreChart/{id}")
    public void updateAldreteScoreChart(@PathVariable(name = "id") Long id, @RequestBody CreateAldreteScoreChartReuest createAldreteScoreChartReuest) {
        this.clinicalInfoWritePlatformService.updateAldreteScoreChart(id,createAldreteScoreChartReuest);
    }

    @PostMapping("/saveDermatologyCaseSheet")
    public Response saveDermatologyCaseSheet(@RequestBody CreateDermatologyCaseSheetRequest createDermatologyCaseSheetRequest) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        return this.clinicalInfoWritePlatformService.saveDermatologyCaseSheet(createDermatologyCaseSheetRequest);
    }

    @GetMapping("/fetchDermatologyCaseSheetByVstId/{vstId}")
    public Map<String, Object> fetchDermatologyCaseSheetByVstId(@PathVariable(name = "vstId") Long vstId) {
        return this.clinicalInfoReadPlatformService.fetchDermatologyCaseSheetByVstId(vstId);
    }

    @PutMapping("/updateDermatologyCaseSheet/{id}/{caseSheetType}")
    public void updateDermatologyCaseSheet(@PathVariable(name = "id") Long id,
                                           @PathVariable(name = "caseSheetType") Integer caseSheetType,
                                           @RequestBody CreateDermatologyCaseSheetRequest createDermatologyCaseSheetRequest) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        this.clinicalInfoWritePlatformService.updateDermatologyCaseSheet(id,createDermatologyCaseSheetRequest,caseSheetType);
    }

    @PostMapping("/saveOpthamologyCaseSheet")
    public Response saveOpthamologyCaseSheet(@RequestBody CreateOpthamologyCaseSheetRequest createOpthamologyCaseSheetRequest) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        return this.clinicalInfoWritePlatformService.saveOpthamologyCaseSheet(createOpthamologyCaseSheetRequest);
    }

    @GetMapping("/fetchOpthamologyCaseSheetByVstId/{vstId}")
    public Map<String, Object> fetchOpthamologyCaseSheetByVstId(@PathVariable(name = "vstId") Long vstId) {
        return this.clinicalInfoReadPlatformService.fetchOpthamologyCaseSheetByVstId(vstId);
    }

    @PutMapping("/updateOpthamologyCaseSheet/{id}/{caseSheetType}")
    public void updateOpthamologyCaseSheet(@PathVariable(name = "id") Long id,
                                           @PathVariable(name = "caseSheetType") Integer caseSheetType,
                                           @RequestBody CreateOpthamologyCaseSheetRequest createOpthamologyCaseSheetRequest) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        this.clinicalInfoWritePlatformService.updateOpthamologyCaseSheet(id,createOpthamologyCaseSheetRequest,caseSheetType);
    }

    @PostMapping("/saveENTCaseSheet")
    public Response saveENTCaseSheet(@RequestBody CreateENTCaseSheetRequest createENTCaseSheetRequest) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        return this.clinicalInfoWritePlatformService.saveENTCaseSheet(createENTCaseSheetRequest);
    }

    @GetMapping("/fetchENTCaseSheetByVstId/{vstId}")
    public Map<String, Object> fetchENTCaseSheetByVstId(@PathVariable(name = "vstId") Long vstId) {
        return this.clinicalInfoReadPlatformService.fetchENTCaseSheetByVstId(vstId);
    }

    @PutMapping("/updateENTCaseSheet/{id}/{caseSheetType}")
    public void updateENTCaseSheet(@PathVariable(name = "id") Long id,
                                   @PathVariable(name = "caseSheetType") Integer caseSheetType,
                                   @RequestBody CreateENTCaseSheetRequest createENTCaseSheetRequest) {
        final AppUser appUser = this.platformSecurityContext.authenticateUser();
        if (appUser.getUser().getIsDoctor() != 1) {
            throw new HimsApplicationContextException("Access Only For Doctors !");
        }
        this.clinicalInfoWritePlatformService.updateENTCaseSheet(id,createENTCaseSheetRequest,caseSheetType);
    }

}

