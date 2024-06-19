package com.ueniweb.swiftwaresolutions.services;

import com.ueniweb.swiftwaresolutions.data.ComplaintDetailsData;
import com.ueniweb.swiftwaresolutions.data.DiagnosisData;
import com.ueniweb.swiftwaresolutions.data.GeneralCaseSheetData;
import com.ueniweb.swiftwaresolutions.data.PatientVisitData;
import com.ueniweb.swiftwaresolutions.domain.*;
import com.ueniweb.swiftwaresolutions.infrastructure.exceptions.NotFoundException;
import com.ueniweb.swiftwaresolutions.repository.DepartmentRepositoryWrapper;
import com.ueniweb.swiftwaresolutions.repository.PatientOpVisitRepositoryWrapper;
import com.ueniweb.swiftwaresolutions.rowmapper.GeneralCaseSheetRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.ueniweb.swiftwaresolutions.utils.DateTimeUtils.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientVisitReadPlatformServiceImpl implements PatientVisitReadPlatformService {


    private final PatientOpVisitRepositoryWrapper patientOpVisitRepositoryWrapper;

    private final JdbcTemplate jdbcTemplate;

    private final DepartmentRepositoryWrapper departmentRepositoryWrapper;

    private final ConsultantRepositoryWrapper consultantRepositoryWrapper;

    private final ClinicalInfoReadPlatformServiceImpl clinicalInfoReadPlatformService;

    @Override
    public List<PatientVisitData> fetchGeneralCashSheetDataByPatientId(Long patientId) {
        List<PatientVisitData> patientVisitDataArrayList = new ArrayList<>();
        try {
            final List<PatientOpVisit> patientOpVisitList = this.patientOpVisitRepositoryWrapper.findPatientOpVisitByPatientId(patientId);
            if (patientOpVisitList == null) {
                throw new NotFoundException("No visit For this Patient id :" + patientId);
            }
            for (PatientOpVisit patientOpVisit: patientOpVisitList) {
                GeneralCaseSheetData generalCaseSheetData;
                final Department department = this.departmentRepositoryWrapper.findOneWithNotFoundDetection(patientOpVisit.getServiceId());
                final Consultant consultant = this.consultantRepositoryWrapper.findOneWithNotFoundDetection(patientOpVisit.getDoctorId());
                PatientVisitData patientVisitData = new PatientVisitData();
                patientVisitData.setDate(patientOpVisit.getDateTime());
                patientVisitData.setDoctorName(consultant.getName());
                patientVisitData.setDepartmentName(department.getName());
                patientVisitData.setVisitId(patientOpVisit.getId());
//                    final GeneralCaseSheetRowMapper generalCaseSheetRowMapper = new GeneralCaseSheetRowMapper();
//                    String whereCondition = " WHERE cs.patId = " + patientId + " AND cs.vstId = " + patientOpVisit.getId();
//                    String qry = "SELECT * " + generalCaseSheetRowMapper.tableSchema() + whereCondition;
//                try {
//                     generalCaseSheetData = this.jdbcTemplate.queryForObject(qry, generalCaseSheetRowMapper);
//                } catch (EmptyResultDataAccessException e) {
//                    generalCaseSheetData = null;
//
//                }
//                final List<ComplaintDetailsData> complaintDataList = clinicalInfoReadPlatformService.fetchComplaintDetailsByVisitId(generalCaseSheetData.getVstId(), 1);
//                generalCaseSheetData.setComplaintDataList(complaintDataList);
//                final List<DiagnosisData> diagnosisDataList = clinicalInfoReadPlatformService.fetchDiagnosisDetailsByVisitId(generalCaseSheetData.getVstId(), 1);
//                generalCaseSheetData.setDiagnosisDetailsData(diagnosisDataList);
//                patientVisitData.setGeneralCaseSheetData(generalCaseSheetData);
                patientVisitDataArrayList.add(patientVisitData);

            }

            return patientVisitDataArrayList;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
