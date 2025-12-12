package com.ueniweb.swiftwaresolutions.repository;

import com.ueniweb.swiftwaresolutions.domain.MedicineAdministrationTiming;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineAdministrationTimingRepository
        extends JpaRepository<MedicineAdministrationTiming, Long> {
            void deleteByEntryId(Long entryId);
}

