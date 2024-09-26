package com.ueniweb.swiftwaresolutions.service;

import com.ueniweb.swiftwaresolutions.data.IpPatientData;
import com.ueniweb.swiftwaresolutions.data.PatientVisitData;
import com.ueniweb.swiftwaresolutions.data.PrePatientData;
import com.ueniweb.swiftwaresolutions.domain.*;
import com.ueniweb.swiftwaresolutions.infrastructure.exceptions.NotFoundException;
import com.ueniweb.swiftwaresolutions.repository.DepartmentRepositoryWrapper;
import com.ueniweb.swiftwaresolutions.repository.PatientOpVisitRepositoryWrapper;
import com.ueniweb.swiftwaresolutions.repository.PatientRepository;
import com.ueniweb.swiftwaresolutions.rowmapper.IpPatientRowMapper;
import com.ueniweb.swiftwaresolutions.rowmapper.IpPatientsRowMapper;
import com.ueniweb.swiftwaresolutions.rowmapper.PrePatientRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class PatientVisitReadPlatformServiceImpl implements PatientVisitReadPlatformService {

    private final PatientOpVisitRepositoryWrapper patientOpVisitRepositoryWrapper;

    private final PatientRepository patientRepository;

    private final JdbcTemplate jdbcTemplate;

    private final DepartmentRepositoryWrapper departmentRepositoryWrapper;

    private final ConsultantRepositoryWrapper consultantRepositoryWrapper;

    @Override
    public List<PatientVisitData> fetchPatientVisitDetailsByPatientId(Long patientId) {
        List<PatientVisitData> patientVisitDataArrayList = new ArrayList<>();
        try {
            final List<PatientOpVisit> patientOpVisitList = this.patientOpVisitRepositoryWrapper.findPatientOpVisitByPatientId(patientId);
            if (patientOpVisitList == null) {
                throw new NotFoundException("No visit For this Patient id :" + patientId);
            }
            for (PatientOpVisit patientOpVisit : patientOpVisitList) {
                final Department department = this.departmentRepositoryWrapper.findOneWithNotFoundDetection(patientOpVisit.getServiceId());
                final Consultant consultant = this.consultantRepositoryWrapper.findOneWithNotFoundDetection(patientOpVisit.getDoctorId());
                PatientVisitData patientVisitData = new PatientVisitData();
                patientVisitData.setDate(patientOpVisit.getDateTime());
                patientVisitData.setDoctorName(consultant.getName());
                patientVisitData.setDepartmentName(department.getName());
                patientVisitData.setVisitId(patientOpVisit.getId());
                patientVisitDataArrayList.add(patientVisitData);
            }
            return patientVisitDataArrayList;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public List<PatientVisitData> fetchPatientVisitDetailsByPatientDisplayNumber(String patientDisplayNumber) {
        List<PatientVisitData> patientVisitDataArrayList = new ArrayList<>();
        try {
            final Patient patient = this.patientRepository.findPatientOpVisitByPatientDisplayNumber(patientDisplayNumber);
            return this.fetchPatientVisitDetailsByPatientId(patient.getId());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public List<PrePatientData> fetchPrePatientDetialsByVstId(Long vstId) {
        log.debug("START of fetchPrePatientDetialsByVstId()  vstId{} ", vstId);
        final PrePatientRowMapper prePatientRowMapper = new PrePatientRowMapper();
        String qry = "SELECT " + prePatientRowMapper.tableSchema() + "WHERE a.pat_id = b.`pat_id` AND d.id=b.pat_id AND a.id ="+vstId;
        System.out.print("patientDetail"+qry);
        log.debug("END of fetchPrePatientDetialsByVstId()");
        return this.jdbcTemplate.query(qry, prePatientRowMapper);
    }
    public List<IpPatientData> fetchIpVisitsByDisplayNo(String display) {
        log.debug("START of fetchIpVisitsByDisplayNo()  vstId{} ", display);
        System.out.print("fetchIpVisitsByDisplayNo"+display);

        final IpPatientRowMapper ipPatientRowMapper = new IpPatientRowMapper();
        String qry = "SELECT " + ipPatientRowMapper.tableSchema() + "  where b.id=c.`pat_id` AND c.id=a.`opvisit_id` AND a.`doc_id`=d.id AND a.`dept_id`=e.`id` AND b.`display_number`="+display;
        System.out.print("fetchIpVisitsByDisplayNo"+qry);
        log.debug("END of fetchIpVisitsByDisplayNo()");
        return this.jdbcTemplate.query(qry, ipPatientRowMapper);
    }
}
