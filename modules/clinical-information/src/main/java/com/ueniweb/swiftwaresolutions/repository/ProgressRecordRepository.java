package com.ueniweb.swiftwaresolutions.repository;

import com.ueniweb.swiftwaresolutions.domain.ProgressRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProgressRecordRepository extends JpaRepository<ProgressRecord, Integer> {
    List<ProgressRecord> findByVisitId(Integer visitId);
    Optional<ProgressRecord> findByVisitIdAndDate(Integer visitId, LocalDate date);
}
