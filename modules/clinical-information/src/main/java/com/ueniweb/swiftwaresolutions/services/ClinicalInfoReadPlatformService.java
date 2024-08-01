package com.ueniweb.swiftwaresolutions.services;


import com.ueniweb.swiftwaresolutions.core.services.Page;
import com.ueniweb.swiftwaresolutions.data.*;
import com.ueniweb.swiftwaresolutions.domain.NeonateCaseSheet;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface ClinicalInfoReadPlatformService {

    List<PrevPrescriptionDetailsData> fetchPrevPrescriptionDetails(Long patId,Integer storeId);

    List<PrevPrescriptionDetailsData> fetchPrescriptionDetailsByVstId(Long vstId,Integer isFromSummary);

    List<PrevPrescriptionDetailsData> fetchPatientLastPrescription(Long patId,Integer storeId);

    List<ComplaintData> fetchComplaintDetails(String complaintName);

    List<DiagnosisData> fetchDiagnosisDetails(String diagName);

    List<GenericData> fetchGenericDetails(String genName);

    List<TimingData> fetchTimingDetails();

    List<DurationData> fetchDurationDetails();

    List<ConsultantData> fetchConsultant(String consultantName);

    List<UnitData> fetchUnitDetails();

    List<LabDepartmentData> fetchLabDepartment();

    List<InvDepartmentData> fetchInvestigationDepartment();

    List<LabResultData> fetchLabResultDetailsByVisitId(Long visitId);

    List<InvestigationData> fetchInvestigationDetailsByGroupId(Long groupId);

    List<LabTestNameData> fetchLabTestNameByGroupId(Long deptId);

    List<TemplateData> fetchPrescriptionTemplates(Long templateType);

    List<PrescTemplateDetailsData> fetchPrescriptionTemplateDetailsById(Long id,Integer storeId);

    List<PrescriptionData> fetchPrescriptionDetailsById(Long id);

    Map<String, Object> fetchGeneralCaseSheetByVstId(Long vstId);

    List<LabOrderedData> fetchLabOrderedDetails(Long patId);

    List<InvestigationOrderedData> fetchInvestigationOrderedDetails(Long patId);

    List<TemplateData> fetchOrderTemplates();

    List<LabTestNameData> fetchLabOrderTemplatesById(Long templateId);

    List<InvestigationData> fetchInvOrderTemplatesById(Long templateId);

    List <PediatricCaseSheetData>fetchPediatricCaseSheetByVstId(Long vstId);

    List<DiscountData> fetchOrderDiscountAmount(Long patId);

    List <NeonateCaseSheetData>fetchNeonateCaseSheetByVstId(Long vstId);

    List <AnthropometryData>fetchAnthropometryByVstId(Long vstId);

    List <OPVitalsData>fetchOpVitalsByVstId(Long vstId);

    List <SummaryTemplateData>fetchSummaryTemplate(Long fieldId);

    Map<String, Object> fetchDentalCaseSheetByVstId(Long vstId);

    List <AntenatelCaseSheetData>fetchAntenatalCaseSheetByVstId(Long vstId);

    Map<String, Object>fetchDischargeSummaryByVstId(Long vstId);

    List <PatientUploadImageData>fetchPatientImageDetails(String patId);

    List <SurgeryCaseSheetData>fetchSurgeryCaseSheetByVstId(Long visitId);

    List <NursingIoData>fetchNursingIoByVstId(Long vstId);

    List <SurgeryCheckListData>fetchSurgeryChecklistByVatId(Long vstId);

    List<IpProcedureCaseSheetData> fetchIpProcedureCaseSheetByVstId(Long vstId);

    List <AldreteScoreChartData>fetchAldreteScoreChartByVstId(Long vstId);

    Map<String, Object> fetchDermatologyCaseSheetByVstId(Long vstId);

}

