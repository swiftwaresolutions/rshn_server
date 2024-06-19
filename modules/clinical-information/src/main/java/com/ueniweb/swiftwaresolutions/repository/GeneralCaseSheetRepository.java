package com.ueniweb.swiftwaresolutions.repository;

import com.ueniweb.swiftwaresolutions.domain.GeneralCaseSheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeneralCaseSheetRepository extends JpaRepository<GeneralCaseSheet, Long> {


    Optional<GeneralCaseSheet> findByPatientId(Long patientId);
}
