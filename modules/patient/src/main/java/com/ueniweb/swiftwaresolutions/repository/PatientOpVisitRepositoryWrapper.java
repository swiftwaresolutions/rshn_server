package com.ueniweb.swiftwaresolutions.repository;

import com.ueniweb.swiftwaresolutions.domain.Patient;
import com.ueniweb.swiftwaresolutions.domain.PatientOpVisit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientOpVisitRepositoryWrapper {

    private final PatientOpVisitRepository patientOpVisitRepository;
    private final PatientRepository patientRepository;

    @Transactional(readOnly = true)
    public List<PatientOpVisit> findPatientOpVisitByPatientId(final Long patientId){
        List<PatientOpVisit> patientOpVisitList =  this.patientOpVisitRepository.findPatientOpVisitByPatientId(patientId);
        if (patientOpVisitList.isEmpty()) {
            return null;
        }
        return patientOpVisitList;
    }
    @Transactional(readOnly = true)
    public Patient findPatientOpVisitByPatientDisplayNumber(final String patientDisplayNumber){
        return this.patientRepository.findPatientOpVisitByPatientDisplayNumber(patientDisplayNumber);
    }
}
