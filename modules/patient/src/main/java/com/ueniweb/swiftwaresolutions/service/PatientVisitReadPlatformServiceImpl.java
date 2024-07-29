package com.ueniweb.swiftwaresolutions.service;

import com.ueniweb.swiftwaresolutions.data.PatientVisitData;
import com.ueniweb.swiftwaresolutions.domain.*;
import com.ueniweb.swiftwaresolutions.infrastructure.exceptions.NotFoundException;
import com.ueniweb.swiftwaresolutions.repository.DepartmentRepositoryWrapper;
import com.ueniweb.swiftwaresolutions.repository.PatientOpVisitRepositoryWrapper;
import com.ueniweb.swiftwaresolutions.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
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

}
