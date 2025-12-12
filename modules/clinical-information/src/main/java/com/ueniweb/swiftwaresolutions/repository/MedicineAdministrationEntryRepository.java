package com.ueniweb.swiftwaresolutions.repository;

import com.ueniweb.swiftwaresolutions.domain.MedicineAdministrationEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineAdministrationEntryRepository
        extends JpaRepository<MedicineAdministrationEntry, Long> {
            void deleteByAdministrationId(Long administrationId);
            List<MedicineAdministrationEntry> findByAdministrationId(Long administrationId);
}
