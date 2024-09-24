package com.ueniweb.swiftwaresolutions.services;

import com.ueniweb.swiftwaresolutions.core.services.PaginationHelper;
import com.ueniweb.swiftwaresolutions.data.*;
import com.ueniweb.swiftwaresolutions.repository.PrescriptionRepository;
import com.ueniweb.swiftwaresolutions.rowmapper.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class ClinicalInfoReadPlatformServiceImpl implements ClinicalInfoReadPlatformService {

    private final JdbcTemplate jdbcTemplate;

    private final PrescriptionRepository prescriptionRepository;
    private final PaginationHelper<PrescriptionData> prescriptionDataPaginationHelper = new PaginationHelper<>();


    @Override
    public List<ComplaintData> fetchComplaintDetails(String complaintName) {
        log.debug("START of fetchComplaintDetails() complaintName{}", complaintName);
        final ComplaintsRowMapper complaintsRowMapper = new ComplaintsRowMapper();

        String qry = "SELECT " + complaintsRowMapper.tableSchema() + " WHERE name like " + "'%" + complaintName + "%'" + " and  is_blocked=0";
        String countQry = "SELECT count(*)" + complaintsRowMapper.schema();
        log.debug("END of fetchComplaintDetails()");
        return this.jdbcTemplate.query(qry, complaintsRowMapper);
    }

    @Override
    public List<DiagnosisData> fetchDiagnosisDetails(String diagName) {
        log.debug("START of fetchDiagnosisDetails() diagName{} ", diagName);
        final DiagnosisRowMapper diagnosisRowMapper = new DiagnosisRowMapper();

        String qry = "SELECT " + diagnosisRowMapper.tableSchema() + " WHERE a.id = b.disease_id AND b.name like " + "'%" + diagName + "%'" + " and a.is_valid = 1 AND b.is_valid = 1  ORDER BY b.name";

        String countQry = "SELECT count(*)" + diagnosisRowMapper.schema();
        log.debug("END of fetchDiagnosisDetails()");
        return this.jdbcTemplate.query(qry, diagnosisRowMapper);
    }

    @Override
    public List<GenericData> fetchGenericDetails(String genName) {
        log.debug("START of fetchGenericDetails()  genName{} ", genName);
        final GenericRowMapper genericRowMapper = new GenericRowMapper();

        String qry = "SELECT " + genericRowMapper.tableSchema() + " WHERE name like " + "'%" + genName + "%'" + " ORDER BY NAME";
        String countQry = "SELECT count(*)" + genericRowMapper.schema();
        log.debug("END of fetchDiagnosisDetails()");
        return this.jdbcTemplate.query(qry, genericRowMapper);
    }

    @Override
    public List<TimingData> fetchTimingDetails() {
        log.debug("START of fetchTimingDetails() ");
        final TimingRowMapper timingRowMapper = new TimingRowMapper();

        String qry = "SELECT " + timingRowMapper.tableSchema() + " WHERE a.is_active=1 ORDER BY NAME";
        String countQry = "SELECT count(*)" + timingRowMapper.schema();
        log.debug("END of fetchTimingDetails()");
        return this.jdbcTemplate.query(qry, timingRowMapper);
    }

    @Override
    public List<DurationData> fetchDurationDetails() {
        log.debug("START of fetchDurationDetails() ");
        final DurationRowMapper durationRowMapper = new DurationRowMapper();

        String qry = "SELECT " + durationRowMapper.tableSchema() + " WHERE a.is_valid=1";
        String countQry = "SELECT count(*)" + durationRowMapper.schema();
        log.debug("END of fetchDurationDetails()");
        return this.jdbcTemplate.query(qry, durationRowMapper);
    }

    @Override
    public List<ConsultantData> fetchConsultant(String consultantName) {
        log.debug("START of fetchConsultant() consultantName{} ", consultantName);
        final ConsultantRowWrapper consultantRowWrapper = new ConsultantRowWrapper();

        String qry = "SELECT " + consultantRowWrapper.tableSchema() + " WHERE name like " + "'%" + consultantName + "%'" + " and  a.is_active=1 ORDER BY a.name";
        String countQry = "SELECT count(*)" + consultantRowWrapper.schema();
        log.debug("END of fetchConsultant()");
        return this.jdbcTemplate.query(qry, consultantRowWrapper);
    }

    @Override
    public List<UnitData> fetchUnitDetails() {
        log.debug("START of fetchUnitDetails() ");
        final UnitRowMapper unitRowMapper = new UnitRowMapper();

        String qry = "SELECT " + unitRowMapper.tableSchema() + " WHERE a.is_valid=1";
        String countQry = "SELECT count(*)" + unitRowMapper.schema();
        log.debug("END of fetchUnitDetails()");
        return this.jdbcTemplate.query(qry, unitRowMapper);
    }

    @Override
    public List<LabDepartmentData> fetchLabDepartment() {
        log.debug("START of fetchLabDepartment() ");
        final LabDepartmentRowMapper labDepartmentRowMapper = new LabDepartmentRowMapper();

        String qry = "SELECT " + labDepartmentRowMapper.tableSchema() + " ORDER BY dept_name";
        String countQry = "SELECT count(*)" + labDepartmentRowMapper.schema();
        log.debug("END of fetchLabDepartment()");
        return this.jdbcTemplate.query(qry, labDepartmentRowMapper);
    }

    @Override
    public List<InvDepartmentData> fetchInvestigationDepartment() {
        log.debug("START of fetchInvestigationDepartment() ");
        final InvDepartmentRowMapper invDepartmentRowMapper = new InvDepartmentRowMapper();

        String qry = "SELECT " + invDepartmentRowMapper.tableSchema() + " WHERE is_group = 1 AND is_display_to_clinical_order = 1 AND is_blocked = 0 ORDER BY NAME";
        String countQry = "SELECT count(*)" + invDepartmentRowMapper.schema();

        log.debug("END of fetchInvestigationDepartment()");
        return this.jdbcTemplate.query(qry, invDepartmentRowMapper);
    }


    public List<LabResultData> fetchLabResultDetailsByVisitId(Long visitId) {
        log.debug("START of fetchLabResultDetailsByVisitId() visitId{} ", visitId);
        final LabResultsRowMapper labResultsRowMapper = new LabResultsRowMapper();
        String whereCondition = " WHERE c.test_id=a.test_id AND c.spec_id=b.spec_code AND c.dept_autoid=d.dept_autoid " +
                "AND d.maj_id=e.maj_id AND f.normalpat_id = e.normalpat_id AND h.fieldid=g.field_id AND a.test_id=h.testid " +
                "AND c.id=i.testreg_id AND g.testval_id=i.testval_id AND d.visit_id = " + visitId;
        String qry = " SELECT " + labResultsRowMapper.tableSchema() + whereCondition + " ORDER BY c.ent_date ";
        log.debug("END of fetchLabResultDetailsByVisitId()");
        return this.jdbcTemplate.query(qry, labResultsRowMapper);
    }

    @Override
    public List<InvestigationData> fetchInvestigationDetailsByGroupId(Long groupId) {
        log.debug("START of fetchInvestigationDetailsByGroupId() groupId{} ", groupId);
        final InvestigationRowMapper investigationRowMapper = new InvestigationRowMapper();

        String whereCondition = " WHERE a1.id = a2.grp AND a2.id = a3.head_id AND a2.is_blocked = 0 AND a1.id = " + groupId + " GROUP BY a2.id";

        if (groupId == 0) {
            whereCondition = " WHERE a1.id = a2.grp AND a2.id = a3.head_id AND a2.is_blocked = 0 AND a1.`is_display_to_clinical_order`= 1 GROUP BY a2.id";
        }
        String qry = " SELECT " + investigationRowMapper.tableSchema() + whereCondition;
        log.debug("END of fetchInvestigationDetailsByGroupId()");
        return this.jdbcTemplate.query(qry, investigationRowMapper);
    }

    @Override
    public List<LabTestNameData> fetchLabTestNameByGroupId(Long deptId) {
        log.debug("START of fetchLabTestNameByGroupId() deptId{} ", deptId);
        final LabTestNameRowMapper labTestNameRowMapper = new LabTestNameRowMapper();
        String whereCondition = " WHERE a1.dept_code = a2.dept_code AND a1.blocked = 0 AND a1.last_value = 1 AND a1.dept_code = " + deptId;
        if (deptId == 0) {
            whereCondition = " WHERE a1.dept_code = a2.dept_code AND a1.blocked = 0 AND a1.last_value = 1";
        }
        String qry = " SELECT " + labTestNameRowMapper.tableSchema() + whereCondition;
        log.debug("END of fetchLabTestNameByGroupId()");
        return this.jdbcTemplate.query(qry, labTestNameRowMapper);
    }

    @Override
    public List<TemplateData> fetchPrescriptionTemplates(Long templateType) {
        log.debug("START of fetchCaseSheetTemplates() templateType{} ",templateType);
        final PrescTemplateRowMapper prescTemplateRowMapper = new PrescTemplateRowMapper();

        String qry = "SELECT a.id,a.name as templateName from ph_template a where a.templateType="+templateType+" and a.is_valid=1";

        log.debug("END of fetchCaseSheetTemplates()");
        return this.jdbcTemplate.query(qry, prescTemplateRowMapper);

    }

    @Override
    public List<PrescTemplateDetailsData> fetchPrescriptionTemplateDetailsById(Long id,Integer storeId) {
        log.debug("START of fetchPrescriptionTemplateDetailsByPrescId() prescId{} ", id);
        final PrescTemplateDetailsRowMapper prescTemplateDetailsRowMapper = new PrescTemplateDetailsRowMapper();
        String whereCondition = " WHERE e.`generic_id`=d.`id` AND a.prods_id=e.id AND a.template_id=b.id AND b.id = " + id;

        String qry = " SELECT " + prescTemplateDetailsRowMapper.tableSchema(storeId) + whereCondition;
        log.debug("END of fetchPrescriptionTemplateDetailsByPrescId()");
        return this.jdbcTemplate.query(qry, prescTemplateDetailsRowMapper);
    }

    @Override
    public List<PrevPrescriptionDetailsData> fetchPrevPrescriptionDetails(Long patId,Integer storeId) {
        log.debug("START of fetchPrevPrescriptionDetails() patId{} ", patId);
        final PrevPrescDetailsRowMapper prevPrescDetailsRowMapper = new PrevPrescDetailsRowMapper();
        String whereCondition = " WHERE a.generic_id=d.id AND a.prods_id=e.id AND a.prescription_id=b.id AND b.pat_id = " + patId + " and a.is_cancelled=0";

        String qry = " SELECT " + prevPrescDetailsRowMapper.tableSchema(storeId) + whereCondition;
        log.debug("END of fetchPrevPrescriptionDetails()");
        return this.jdbcTemplate.query(qry, prevPrescDetailsRowMapper);
    }

    @Override
    public List<PrevPrescriptionDetailsData> fetchPrescriptionDetailsByVstId(Long vstId,Integer isFromSummary) {
        log.debug("START of fetchPrescriptionDetailsByVstId() vstId{} isFromSummary{}", vstId,isFromSummary);
        final PrevPrescDetailsRowMapper prevPrescDetailsRowMapper = new PrevPrescDetailsRowMapper();
        String whereCondition = " WHERE a.generic_id=d.id AND a.prods_id=e.id AND a.prescription_id=b.id AND b.visit_id = " + vstId + " and b.isFromSummary="+isFromSummary+" and a.is_cancelled=0";

        String qry = " SELECT " + prevPrescDetailsRowMapper.tableSchema(1) + whereCondition;
        log.debug("END of fetchPrescriptionDetailsByVstId()");
        return this.jdbcTemplate.query(qry, prevPrescDetailsRowMapper);
    }

    @Override
    public List<PrevPrescriptionDetailsData> fetchPatientLastPrescription(Long patId,Integer storeId) {
        log.debug("START of fetchPrevPrescriptionDetails() patId{} ", patId);
        final PrevPrescDetailsRowMapper prevPrescDetailsRowMapper = new PrevPrescDetailsRowMapper();
        String display = this.prescriptionRepository.getPrescDisplay(patId);

        String whereCondition = " WHERE a.generic_id=d.id AND a.prods_id=e.id AND a.prescription_id=b.id AND b.display =" + display + " and a.is_cancelled=0";

        String qry = " SELECT " + prevPrescDetailsRowMapper.tableSchema(storeId) + whereCondition;

        log.debug("END of fetchPrevPrescriptionDetails()");
        return this.jdbcTemplate.query(qry, prevPrescDetailsRowMapper);
    }

    public List<PrescriptionData> fetchPrescriptionDetailsById(Long id) {
        log.debug("START of fetchPrescriptionDetailsById() id {} ", id);
        final PrescriptionRowMapper prescriptionRowMapper = new PrescriptionRowMapper();
        String whereCond = "";
        if (id != null) {
            whereCond = " WHERE prd.is_cancelled=0 and  pr.id ='" + id + "' ";
        }
        String qry = "SELECT " + prescriptionRowMapper.tableSchema() + whereCond;
        System.out.print("vada"+qry);

        log.debug("END of fetchPrescriptionDetailsById() id {} ", id);
        return this.jdbcTemplate.query(qry, prescriptionRowMapper);
    }

    @Override
    public Map<String, Object> fetchGeneralCaseSheetByVstId(Long vstId) {
        log.debug("START of fetchGeneralCaseSheetByVstId() vstId{} ", vstId);
        final GeneralCaseSheetRowMapper generalCaseSheetRowMapper = new GeneralCaseSheetRowMapper();
        String whereCondition = " WHERE cs.vstId = " + vstId;

        String qry = " SELECT " + generalCaseSheetRowMapper.schema() + whereCondition;

        log.debug("END of fetchGeneralCaseSheetByVstId()");
        final List<GeneralCaseSheetData> generalDataList = this.jdbcTemplate.query(qry, generalCaseSheetRowMapper);
        for (GeneralCaseSheetData generalCaseSheetData : generalDataList) {
            final List<ComplaintDetailsData> complaintDataList = fetchComplaintDetailsByVisitId(generalCaseSheetData.getVstId(), 1);
            generalCaseSheetData.setComplaintDataList(complaintDataList);
            final List<DiagnosisData> diagnosisDataList = fetchDiagnosisDetailsByVisitId(generalCaseSheetData.getVstId(), 1);
            generalCaseSheetData.setDiagnosisDetailsData(diagnosisDataList);
        }
        Map<String, Object> generalCaseSheetMap = new HashMap<>();
        generalCaseSheetMap.put("status", !generalDataList.isEmpty());
        if (generalDataList.isEmpty()) {
            generalCaseSheetMap.put("message", "No Details Found");
        } else {
            generalCaseSheetMap.put("data", generalDataList);
        }
        return generalCaseSheetMap;
    }

    public List<ComplaintDetailsData> fetchComplaintDetailsByVisitId(Long vstId, Integer caseSheetType) {
        log.debug("START of fetchComplaintDetailsByVisitId() vstId{} caseSheetType{} ", vstId, caseSheetType);
        final ComplaintDetailsRowMapper complaintDetailsRowMapper = new ComplaintDetailsRowMapper();

        String qry = " SELECT " + complaintDetailsRowMapper.tableSchema() + "WHERE a.`complaintId`=b.`id` AND a.isValid=1 AND a.`vstId`=" + vstId + " AND a.`caseSheetType`=" + caseSheetType;
        log.debug("END of fetchComplaintDetailsByVisitId()");
        return this.jdbcTemplate.query(qry, complaintDetailsRowMapper);
    }

    public List<DiagnosisData> fetchDiagnosisDetailsByVisitId(Long vstId, Integer caseSheetType) {
        log.debug("START of fetchDiagnosisDetailsByVisitId() vstId{} caseSheetType{} ", vstId, caseSheetType);
        final DiagnosisDetailsRowMapper diagnosisDetailsRowMapper = new DiagnosisDetailsRowMapper();

        String qry = " SELECT " + diagnosisDetailsRowMapper.tableSchema() + " WHERE a.`id`=b.`diagnosisId` AND b.`vstId`=" + vstId + " AND b.`caseSheetType`=" + caseSheetType + " AND b.`isValid`=1 ";

        log.debug("END of fetchDiagnosisDetailsByVisitId()");
        return this.jdbcTemplate.query(qry, diagnosisDetailsRowMapper);
    }

    @Override
    public List<LabOrderedData> fetchLabOrderedDetails(Long patId) {
        log.debug("START of fetchLabOrderedDetails() patId{} ", patId);
        final LabOrderedDetailsRowMapper labOrderedDetailsRowMapper = new LabOrderedDetailsRowMapper();

        String qry = " SELECT a.id,a.pat_id as patId,b.test_name as testName,a.unit as units,DATE_FORMAT(DATE(a.dtm),'%d/%m/%Y') as date FROM x_temp_lab_bill_details a,lab_config_master_test b WHERE a.test_id=b.test_id and a.finalBillId =0 AND a.pat_id=" + patId;
        log.debug("END of fetchLabOrderedDetails()");
        return this.jdbcTemplate.query(qry, labOrderedDetailsRowMapper);
    }
    @Override
    public List<DiscountData> fetchOrderDiscountAmount(Long patId) {
        log.debug("START of fetchOrderDiscountAmount() patId{} ", patId);
        final DiscountRowMapper discountRowMapper = new DiscountRowMapper();

        String qry = " SELECT a.id,a.pat_id as patId,a.datetime,SUM(a.disc_amt) as discAmt FROM `x_temp_inv_lab_disc`a WHERE a.pat_id="+patId+" AND a.final_bill_id=0";
        log.debug("END of fetchOrderDiscountAmount()");
        return this.jdbcTemplate.query(qry,discountRowMapper);
    }
    @Override
    public List<InvestigationOrderedData> fetchInvestigationOrderedDetails(Long patId) {
        log.debug("START of fetchInvestigationOrderedDetails() patId{} ", patId);
        final InvestigationOrderedRowMapper investigationOrderedRowMapper = new InvestigationOrderedRowMapper();

        String qry = " select a.id,a.pat_id as patId,b.name as procName,a.unit as units,date_format(date(a.dtm),'%d/%m/%Y') as date from x_temp_cash_bill_details a,cash_config_head b where a.particular_id=b.id  and a.finalBillId =0 AND a.pat_id=" + patId;
        log.debug("END of fetchInvestigationOrderedDetails()");
        return this.jdbcTemplate.query(qry, investigationOrderedRowMapper);
    }

    @Override
    public List<TemplateData> fetchOrderTemplates() {
        log.debug("START of fetchOrderTemplates() ");
        final OrderTemplateRowMapper orderTemplateRowMapper = new OrderTemplateRowMapper();

        String qry = "SELECT a.id,a.templateName AS templateName FROM cli_investigation_template a WHERE isActive=1";

        log.debug("END of fetchOrderTemplates()");
        return this.jdbcTemplate.query(qry, orderTemplateRowMapper);

    }

    @Override
    public List<LabTestNameData> fetchLabOrderTemplatesById(Long templateId) {
        log.debug("START of fetchLabOrderTemplatesById() templateId{} ", templateId);
        final LabOrderTemplateRowMapper labOrderTemplateRowMapper = new LabOrderTemplateRowMapper();
        String whereCondition = " WHERE a1.dept_code = a2.dept_code AND a1.test_id = a3.procedureId AND a3.templateId = " + templateId + " AND a1.blocked = 0 AND a1.last_value = 1";

        String qry = " SELECT " + labOrderTemplateRowMapper.tableSchema() + whereCondition;
        log.debug("END of fetchLabOrderTemplatesById()");
        return this.jdbcTemplate.query(qry, labOrderTemplateRowMapper);
    }

    @Override
    public List<InvestigationData> fetchInvOrderTemplatesById(Long templateId) {
        log.debug("START of fetchInvOrderTemplatesById() templateId{} ", templateId);
        final InvestigationTemplateRowMapper investigationTemplateRowMapper = new InvestigationTemplateRowMapper();
        String whereCondition = " WHERE a1.id = a2.grp AND a2.id = a3.head_id AND a2.id = a4.procedureId AND a4.templateId = " + templateId + " AND a1.is_display_to_clinical_order = 1 AND a2.is_blocked = 0 AND a3.acc_head_id = 1";

        String qry = " SELECT " + investigationTemplateRowMapper.tableSchema() + whereCondition;
        log.debug("END of fetchInvOrderTemplatesById()");
        return this.jdbcTemplate.query(qry, investigationTemplateRowMapper);
    }

    @Override
    public List<PediatricCaseSheetData> fetchPediatricCaseSheetByVstId(Long vstId) {
        log.debug("START of fetchPediatricCaseSheetByVstId() vstId{} ", vstId);
        final PediatricCasesheetRowMapper pediatricCasesheetRowMapper = new PediatricCasesheetRowMapper();
        String whereCondition = " WHERE a.vstId = " + vstId;
        String qry = " SELECT " + pediatricCasesheetRowMapper.schema() + whereCondition;
        final List<PediatricCaseSheetData> pediatricCaseSheetDataList = this.jdbcTemplate.query(qry, pediatricCasesheetRowMapper);
        for (PediatricCaseSheetData pediatricCaseSheetData : pediatricCaseSheetDataList) {
            final List<ComplaintDetailsData> complaintDataList = fetchComplaintDetailsByVisitId(pediatricCaseSheetData.getVisitId(), 2);
            pediatricCaseSheetData.setComplaintDataList(complaintDataList);
            log.debug("END of fetchPediatricCaseSheetByVstId()");
        }
        return pediatricCaseSheetDataList;
    }


    @Override
    public List<NeonateCaseSheetData> fetchNeonateCaseSheetByVstId(Long vstId) {
        log.debug("START of fetchNeonateCaseSheetByVstId() vstId{} ", vstId);
        final NeonateCasesheetRowMapper neonateCasesheetRowMapper = new NeonateCasesheetRowMapper();
        String whereCondition = " WHERE a.vstId = " + vstId;
        String qry = " SELECT " + neonateCasesheetRowMapper.schema() + whereCondition;
        final List<NeonateCaseSheetData> neonateCaseSheetDataList = this.jdbcTemplate.query(qry, neonateCasesheetRowMapper);
        for (NeonateCaseSheetData neonateCaseSheetData : neonateCaseSheetDataList) {
            final List<ComplaintDetailsData> complaintDataList = fetchComplaintDetailsByVisitId(neonateCaseSheetData.getVisitId(), 3);
            neonateCaseSheetData.setComplaintDataList(complaintDataList);
            log.debug("END of fetchNeonateCaseSheetByVstId()");

        }
        return neonateCaseSheetDataList;
    }

    public List<AnthropometryData> fetchAnthropometryByVstId(Long vstId) {
        log.debug("START of fetchAnthropometryByVstId() vstId{}", vstId);
        final AnthropometryRowMapper anthropometryRowMapper = new AnthropometryRowMapper();

        String qry = " SELECT " + anthropometryRowMapper.tableSchema() + "WHERE a.is_valid=1 AND a.`vstId`=" + vstId;
        log.debug("END of fetchAnthropometryByVstId()");
        return this.jdbcTemplate.query(qry, anthropometryRowMapper);
    }

    @Override
    public List<OPVitalsData> fetchOpVitalsByVstId(Long vstId) {
        log.debug("START of fetchOpVitalsByVstId()  vstId{} ", vstId);
        final OPVitalsRowMapper opVitalsRowmapper = new OPVitalsRowMapper();

        String qry = "SELECT " + opVitalsRowmapper.tableSchema() + " WHERE vstId = "+vstId;

        log.debug("END of fetchOpVitalsByVstId()");
        return this.jdbcTemplate.query(qry, opVitalsRowmapper);
    }

    @Override
    public List<SummaryTemplateData> fetchSummaryTemplate(Long fieldId) {
        log.debug("START of fetchSummaryTemplate()  fieldId{} ", fieldId);
        final SummaryTemplateRowMapper summaryTemplateRowMapper = new SummaryTemplateRowMapper();

        String qry = "SELECT id,name,details from cli_discharge_summary_template where fieldId= "+fieldId;

        log.debug("END of fetchSummaryTemplate()");
        return this.jdbcTemplate.query(qry, summaryTemplateRowMapper);
    }
    @Override
    public Map<String, Object> fetchDentalCaseSheetByVstId(Long vstId) {
        log.debug("START of fetchDentalCaseSheetByVstId() vstId{} ", vstId);
        final DentalCaseSheetRowMapper dentalCaseSheetRowMapper = new DentalCaseSheetRowMapper();

        String qry = " SELECT " + dentalCaseSheetRowMapper.schema() + " WHERE vstId = "+vstId;
        log.debug("END of fetchDentalCaseSheetByVstId()");

        final List<DentalCaseSheetData> dentalDataList = this.jdbcTemplate.query(qry, dentalCaseSheetRowMapper);
        for (DentalCaseSheetData dentalCaseSheetData : dentalDataList) {
            final List<ComplaintDetailsData> complaintDataList = fetchComplaintDetailsByVisitId(dentalCaseSheetData.getVstId(), 5);
            dentalCaseSheetData.setComplaintDataList(complaintDataList);
            final List<DentalTeethData> teethDataList = fetchTeethDetailsByVisitId(dentalCaseSheetData.getVstId());
            dentalCaseSheetData.setTeethDataList(teethDataList);
            final List<DentalTeethTreatData> teethTreatDataList = fetchTeethTreatDetailsByVisitId(dentalCaseSheetData.getVstId());
            dentalCaseSheetData.setTeethTreatDataList(teethTreatDataList);
        }

        Map<String, Object> dentalCaseSheetMap = new HashMap<>();
        dentalCaseSheetMap.put("status", !dentalDataList.isEmpty());
        if (dentalDataList.isEmpty()) {
            dentalCaseSheetMap.put("message", "No Details Found");
        } else {
            dentalCaseSheetMap.put("data", dentalDataList);
        }
        return dentalCaseSheetMap;
    }

    public List<DentalTeethData> fetchTeethDetailsByVisitId(Long vstId) {
        log.debug("START of fetchTeethDetailsByVisitId() vstId{} caseSheetType{} ", vstId);
        final DentalTeethRowMapper teethRowMapper = new DentalTeethRowMapper();

        String qry = " SELECT " + teethRowMapper.tableSchema() + "WHERE a.isValid=1 AND a.`vstId`=" + vstId;
        log.debug("END of fetchTeethDetailsByVisitId()");
        return this.jdbcTemplate.query(qry, teethRowMapper);
    }

    public List<DentalTeethTreatData> fetchTeethTreatDetailsByVisitId(Long vstId) {
        log.debug("START of fetchTreatTeethDetailsByVisitId() vstId{} caseSheetType{} ", vstId);
        final DentalTeethTreatRowMapper teethTreatRowMapper = new DentalTeethTreatRowMapper();

        String qry = " SELECT " + teethTreatRowMapper.tableSchema() + "WHERE a.isValid=1 AND a.`vstId`=" + vstId;
        log.debug("END of fetchTreatTeethDetailsByVisitId()");
        return this.jdbcTemplate.query(qry, teethTreatRowMapper);
    }
    @Override
    public List<AntenatelCaseSheetData> fetchAntenatalCaseSheetByVstId(Long vstId) {
        //  log.debug("START of fetchAntenatalByVstId() vstId{} ", vstId);
        System.out.print(vstId);
        final AntenatalCaseSheetRowMapper antenatalCaseSheetRowMapper = new AntenatalCaseSheetRowMapper();
        String whereCondition = " WHERE a.vstId = " + vstId;
        String qry = " SELECT " + antenatalCaseSheetRowMapper.schema() + whereCondition;
        final List<AntenatelCaseSheetData> antenatelCaseSheetDataList = this.jdbcTemplate.query(qry, antenatalCaseSheetRowMapper);
        for (AntenatelCaseSheetData antenatelCaseSheetData : antenatelCaseSheetDataList) {
            final List<AntenatalPreData> antenatalPreDataList = fetchAntenatelPreByVstId(antenatelCaseSheetData.getId());
            antenatelCaseSheetData.setAntenatalPreData(antenatalPreDataList);
            log.debug("END of fetchAntenatalCaseSheetByVstId()");

        }
        return antenatelCaseSheetDataList;
    }
    public List<AntenatalPreData> fetchAntenatelPreByVstId(Long ancId) {
        log.debug("START of fetchAntenatelPreByVisitId() vstId{} ", ancId);
        final AntenatalPreRowMapper antenatalPreRowMapper = new AntenatalPreRowMapper();
        String whereCondition = " WHERE a.ancId = " + ancId +" AND a.isValid=1";

        String qry = " SELECT " + antenatalPreRowMapper.tableSchema() + whereCondition;

        log.debug("END of fetchAntenatelPreByVisitId()");
        return this.jdbcTemplate.query(qry, antenatalPreRowMapper);

    }

    public List<DischargeDiagnosisData> fetchDisDiagnosisDetailsByvisitId(Long vstId) {
        log.debug("START of fetchDisDiagnosisDetailsById() vstId{} ", vstId);
        final DischargeDiagnosisRowMapper diagnosisDetailsRowMapper = new DischargeDiagnosisRowMapper();

        String qry = " SELECT " + diagnosisDetailsRowMapper.tableSchema() + "WHERE a.summaryId = b.id AND c.id=a.diagnosisId AND a.isValid=1 AND b.`vstId`=" + vstId;

        log.debug("END of fetchDisDiagnosisDetailsById()");
        return this.jdbcTemplate.query(qry, diagnosisDetailsRowMapper);
    }

    public List<DischargeLabInvSummaryData> fetchDisLabInvSumDetailsByvisitId(Long vstId) {
        log.debug("START of fetchDisLabInvSumDetailsByvisitId() vstId{} ", vstId);
        final DischargeInvLabSumRowMapper disInvLabDetailsRowMapper = new DischargeInvLabSumRowMapper();

        String qry = " SELECT " + disInvLabDetailsRowMapper.tableSchema() + "WHERE a.summaryId = b.id AND a.isActive=1 AND b.`vstId`=" + vstId;

        log.debug("END of fetchDisLabInvSumDetailsByvisitId()");
        return this.jdbcTemplate.query(qry, disInvLabDetailsRowMapper);
    }

    public List<DisSumConsultantData> fetchDisSumConsultantById(Long id) {
        log.debug("START of fetchDisSumConsultantById() id{} ", id);
        final DisSumConsultantRowMapper disSumConsultantRowMapper = new DisSumConsultantRowMapper();

        String qry = " SELECT a.id,a.summaryId,a.consultantId,b.name as name from cli_discharge_summary_consultant a,rec_config_msc_consultants b where a.consultantId=b.id and a.isValid=1 and summaryId="+ id;

        log.debug("END of fetchDisSumConsultantById()");
        return this.jdbcTemplate.query(qry,disSumConsultantRowMapper);
    }
    public List<DisSumDeptData> fetchDisSumDeptById(Long id) {
        log.debug("START of fetchDisSumDeptById() id{} ", id);
        final DisSumDeptRowMapper disSumConsultantRowMapper = new DisSumDeptRowMapper();

        String qry = " SELECT a.id,a.summaryId,a.deptId,b.name as name from cli_discharge_summary_department a,rec_config_msc_departments b where a.deptId=b.id and a.isValid=1 and  summaryId="+ id;

        log.debug("END of fetchDisSumDeptById()");
        return this.jdbcTemplate.query(qry,disSumConsultantRowMapper);
    }
    @Override
    public Map<String, Object> fetchDischargeSummaryByVstId(Long vstId) {
        log.debug("START of fetchDischargeSummaryByVstId() vstId{} ", vstId);
        final DischargeSummaryRowMapper dischargeSummaryRowMapper = new DischargeSummaryRowMapper();

        String qry = " SELECT " + dischargeSummaryRowMapper.schema() + " WHERE isActive=1 and vstId = "+vstId;
        log.debug("END of fetchDischargeSummaryByVstId()");
        System.out.println("qry : "+qry  );
        final List<DischargeSummaryData> dischargeSummaryDataList = this.jdbcTemplate.query(qry, dischargeSummaryRowMapper);
        for (DischargeSummaryData dischargeSummaryData : dischargeSummaryDataList) {

        final List<DischargeDiagnosisData> diagnosisDataList = fetchDisDiagnosisDetailsByvisitId(dischargeSummaryData.getVstId());
        dischargeSummaryData.setDischargeDiagnosisDataList(diagnosisDataList);

        final List<DischargeLabInvSummaryData> disLabInvSumDataList = fetchDisLabInvSumDetailsByvisitId(dischargeSummaryData.getVstId());
        dischargeSummaryData.setDischargeLabInvSummaryDataList(disLabInvSumDataList);

        final List<DisSumConsultantData> disSumConsultantDataList = fetchDisSumConsultantById(dischargeSummaryData.getId());
        dischargeSummaryData.setDisSumConsultantDataList(disSumConsultantDataList);

        final List<DisSumDeptData> disSumDeptDataList = fetchDisSumDeptById(dischargeSummaryData.getId());
        dischargeSummaryData.setDisSumDeptDataList(disSumDeptDataList);
        }

        Map<String, Object> dischargeSummaryMap = new HashMap<>();
        dischargeSummaryMap.put("status", !dischargeSummaryDataList.isEmpty());
        if (dischargeSummaryDataList.isEmpty()) {
            dischargeSummaryMap.put("message", "No Details Found");
        } else {
            dischargeSummaryMap.put("data", dischargeSummaryDataList);
        }
        return dischargeSummaryMap;

    }
    public List<PatientUploadImageData> fetchPatientImageDetails(String patId) {
        log.debug("START of fetchXrayImageDetails() id{} ", patId);
        final XrayImagesRowMapper xrayImagesRowMapper = new XrayImagesRowMapper();

        String qry = "SELECT "+xrayImagesRowMapper.schema()+"WHERE x.pat_id ="+ patId;
        log.debug("END of fetchXrayImageDetails()");
        return this.jdbcTemplate.query(qry,xrayImagesRowMapper);
    }
    @Override
    public List<SurgeryCaseSheetData> fetchSurgeryCaseSheetByVstId(Long visitId) {
        //  log.debug("START of fetchSurgeryCaseSheetByVstId() visitId{} ", visitId);
        System.out.print(visitId);
        final SurgeryCaseSheetRowmapper surgeryCaseSheetRowmapper = new SurgeryCaseSheetRowmapper();
        String whereCondition = " WHERE a.visitId = " + visitId;
        String qry = " SELECT " + surgeryCaseSheetRowmapper.schema() + whereCondition;
        final List<SurgeryCaseSheetData> surgeryCaseSheetDataList = this.jdbcTemplate.query(qry, surgeryCaseSheetRowmapper);
        for (SurgeryCaseSheetData surgeryCaseSheetData : surgeryCaseSheetDataList) {
            final List<SurgeryNurseData> surgeryNurseDataList = fetchSurgeryNurseBySurId(surgeryCaseSheetData.getId());
            surgeryCaseSheetData.setSurgeryNurseData(surgeryNurseDataList);
            final List<SurgerySurgonData> surgerySurgonDataList = fetchSurgerySurgonBySurId(surgeryCaseSheetData.getId());
            surgeryCaseSheetData.setSurgerySurgonData(surgerySurgonDataList);
            final List<SurgeryData> surgeryDataList = fetchSurgeryNameBySurId(surgeryCaseSheetData.getId());
            surgeryCaseSheetData.setSurgeryDataList(surgeryDataList);

            // log.debug("END of fetchSurgeryCaseSheetByVstId()");

        }
        return surgeryCaseSheetDataList;
    }
    public List<SurgeryNurseData> fetchSurgeryNurseBySurId(Long surId) {
        log.debug("START of fetchSurgeryNurseBySurId() SurId{} ", surId);
        final SurgeryNurseRowMapper surgeryNurseRowMapper = new SurgeryNurseRowMapper();
        String whereCondition = " WHERE a.surId = " + surId +" AND a.isValid=1";

        String qry = " SELECT " + surgeryNurseRowMapper.tableSchema() + whereCondition;

        log.debug("END of fetchAntenatelPreByVisitId()");
        System.out.print((qry));
        return this.jdbcTemplate.query(qry, surgeryNurseRowMapper);

    }

    public List<SurgerySurgonData> fetchSurgerySurgonBySurId(Long surId) {

        log.debug("START of fetchSurgerySurgonBySurId() SurId{} ", surId);

        final SurgerySergonRowMaper surgerySergonRowMaper = new SurgerySergonRowMaper();
        String whereCondition = " WHERE a.surId = " + surId +" AND a.isValid=1 AND b.`id`=a.`surgonId`";

        String qry = " SELECT " + surgerySergonRowMaper.tableSchema() + whereCondition;

        log.debug("END of fetchSurgerySurgonBySurId()");
        System.out.print((qry));
        return this.jdbcTemplate.query(qry, surgerySergonRowMaper);
    }
    public List<SurgeryData> fetchSurgeryNameBySurId(Long surId) {

        log.debug("START of fetchSurgeryNameBySurId() SurId{} ", surId);

        final SurgeryNameRowMapper surgeryNameRowMapper = new SurgeryNameRowMapper();
        String whereCondition = " WHERE a.surId = " + surId +" AND a.isValid=1 ";

        String qry = " SELECT " + surgeryNameRowMapper.tableSchema() + whereCondition;

        log.debug("END of fetchSurgeryNameBySurId()");
        System.out.print((qry));
        return this.jdbcTemplate.query(qry, surgeryNameRowMapper);
    }
    @Override
    public List<NursingIoData> fetchNursingIoByVstId(Long vstId) {
        log.debug("START of fetchNursingIoByVstId()  vstId{} ", vstId);
        final NursingIoSheetRowMapper nursingIoSheetRowMapper = new NursingIoSheetRowMapper();

        String qry = "SELECT " + nursingIoSheetRowMapper.tableSchema() + " WHERE visitId = " + vstId;

        log.debug("END of fetchNursingIoByVstId()");
        return this.jdbcTemplate.query(qry, nursingIoSheetRowMapper);
    }
    @Override
    public List<SurgeryCheckListData> fetchSurgeryChecklistByVatId(Long vstId) {
        log.debug("START of fetchSurgeryChecklistByVatId()  vstId{} ", vstId);
        final SurgeryChecklistRowMapper surgeryChecklistRowMapper = new SurgeryChecklistRowMapper();

        String qry = "SELECT " + surgeryChecklistRowMapper.tableSchema() + " WHERE visitId = " + vstId;

        log.debug("END of fetchSurgeryChecklistByVatId()");
        return this.jdbcTemplate.query(qry, surgeryChecklistRowMapper);
    }
    public List<IpProcedureCaseSheetData> fetchIpProcedureCaseSheetByVstId(Long vstId) {
        log.debug("START of fetchIpProcedureCaseSheetByVstId()  vstId{} ", vstId);
        final IpProcedureCaseSheetRowMapper ipProcedureCaseSheetRowMapper = new IpProcedureCaseSheetRowMapper();

        String qry = "SELECT " + ipProcedureCaseSheetRowMapper.tableSchema() + "WHERE vstId = "+vstId +" AND isCancelled =0 ORDER BY id DESC;";

        log.debug("END of fetchIpProcedureCaseSheetByVstId()");
        return this.jdbcTemplate.query(qry, ipProcedureCaseSheetRowMapper);
    }
    @Override
    public List<AldreteScoreChartData> fetchAldreteScoreChartByVstId(Long vstId) {
        log.debug("START of fetchAldreteScoreChartByVstId()  vstId{} ", vstId);
        final AldreteScoreChartRowMapper aldreteScoreChartRowMapper = new AldreteScoreChartRowMapper();

        String qry = "SELECT " + aldreteScoreChartRowMapper.tableSchema() + " WHERE visitId = " + vstId;

        log.debug("END of fetchAldreteScoreChartByVstId()");
        return this.jdbcTemplate.query(qry, aldreteScoreChartRowMapper);
    }

    @Override
    public Map<String, Object> fetchDermatologyCaseSheetByVstId(Long vstId) {
        log.debug("START of fetchDermatologyCaseSheetByVstId() vstId{} ", vstId);
        final DermatologyCaseSheetRowMapper dermatologyCaseSheetRowMapper = new DermatologyCaseSheetRowMapper();
        String whereCondition = " WHERE cs.vstId = " + vstId;

        String qry = " SELECT " + dermatologyCaseSheetRowMapper.schema() + whereCondition;

        log.debug("END of fetchDermatologyCaseSheetByVstId()");
        final List<DermatologyCaseSheetData> dermatologyCaseSheetDataList = this.jdbcTemplate.query(qry, dermatologyCaseSheetRowMapper);
        for (DermatologyCaseSheetData dermatologyCaseSheetData : dermatologyCaseSheetDataList) {
            final List<ComplaintDetailsData> complaintDataList = fetchComplaintDetailsByVisitId(dermatologyCaseSheetData.getVstId(), 6);
            dermatologyCaseSheetData.setComplaintDataList(complaintDataList);
            final List<DiagnosisData> diagnosisDataList = fetchDiagnosisDetailsByVisitId(dermatologyCaseSheetData.getVstId(), 6);
            dermatologyCaseSheetData.setDiagnosisDetailsData(diagnosisDataList);
        }
        Map<String, Object> dermatologyCaseSheetMap = new HashMap<>();
        dermatologyCaseSheetMap.put("status", !dermatologyCaseSheetDataList.isEmpty());
        if (dermatologyCaseSheetDataList.isEmpty()) {
            dermatologyCaseSheetMap.put("message", "No Details Found");
        } else {
            dermatologyCaseSheetMap.put("data", dermatologyCaseSheetDataList);
        }
        return dermatologyCaseSheetMap;
    }

    @Override
    public Map<String, Object> fetchOpthamologyCaseSheetByVstId(Long vstId) {
        log.debug("START of fetchDermatologyCaseSheetByVstId() vstId{} ", vstId);
        final OpthamologyCaseSheetRowMapper opthamologyCaseSheetRowMapper = new OpthamologyCaseSheetRowMapper();
        String whereCondition = " WHERE cs.vstId = " + vstId;

        String qry = " SELECT " + opthamologyCaseSheetRowMapper.schema() + whereCondition;

        log.debug("END of fetchOpthamologyCaseSheetByVstId()");
        final List<OpthamologyCaseSheetData> opthamologyCaseSheetDataList = this.jdbcTemplate.query(qry, opthamologyCaseSheetRowMapper);
        for (OpthamologyCaseSheetData opthamologyCaseSheetData : opthamologyCaseSheetDataList) {
            final List<ComplaintDetailsData> complaintDataList = fetchComplaintDetailsByVisitId(opthamologyCaseSheetData.getVstId(), 8);
            opthamologyCaseSheetData.setComplaintDataList(complaintDataList);
            final List<DiagnosisData> diagnosisDataList = fetchDiagnosisDetailsByVisitId(opthamologyCaseSheetData.getVstId(), 8);
            opthamologyCaseSheetData.setDiagnosisDetailsData(diagnosisDataList);
        }
        Map<String, Object> opthamologyCaseSheetMap = new HashMap<>();
        opthamologyCaseSheetMap.put("status", !opthamologyCaseSheetDataList.isEmpty());
        if (opthamologyCaseSheetDataList.isEmpty()) {
            opthamologyCaseSheetMap.put("message", "No Details Found");
        } else {
            opthamologyCaseSheetMap.put("data", opthamologyCaseSheetDataList);
        }
        return opthamologyCaseSheetMap;
    }

    @Override
    public Map<String, Object> fetchENTCaseSheetByVstId(Long vstId) {
        log.debug("START of fetchENTCaseSheetByVstId() vstId{} ", vstId);
        final ENTCaseSheetRowMapper entCaseSheetRowMapper = new ENTCaseSheetRowMapper();
        String whereCondition = " WHERE cs.vstId = " + vstId;

        String qry = " SELECT " + entCaseSheetRowMapper.schema() + whereCondition;

        log.debug("END of fetchENTCaseSheetByVstId()");
        final List<ENTCaseSheetData> entCaseSheetDataList = this.jdbcTemplate.query(qry, entCaseSheetRowMapper);
        for (ENTCaseSheetData entCaseSheetData : entCaseSheetDataList) {
            final List<ComplaintDetailsData> complaintDataList = fetchComplaintDetailsByVisitId(entCaseSheetData.getVstId(), 9);
            entCaseSheetData.setComplaintDataList(complaintDataList);
            final List<DiagnosisData> diagnosisDataList = fetchDiagnosisDetailsByVisitId(entCaseSheetData.getVstId(), 9);
            entCaseSheetData.setDiagnosisDetailsData(diagnosisDataList);
        }
        Map<String, Object> entCaseSheetMap = new HashMap<>();
        entCaseSheetMap.put("status", !entCaseSheetDataList.isEmpty());
        if (entCaseSheetDataList.isEmpty()) {
            entCaseSheetMap.put("message", "No Details Found");
        } else {
            entCaseSheetMap.put("data", entCaseSheetDataList);
        }
        return entCaseSheetMap;
    }
}
