package com.ueniweb.swiftwaresolutions.repository;

import com.ueniweb.swiftwaresolutions.domain.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository
        extends JpaRepository<Medication, Long> {
}

