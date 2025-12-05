package com.ueniweb.swiftwaresolutions.repository;

import com.ueniweb.swiftwaresolutions.domain.NursingChart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NursingChartRepository extends JpaRepository<NursingChart, Long> {

    Optional<NursingChart> findByVisitId(Long visitId);
    
    Optional<NursingChart> findByVisitIdAndPatId(Long visitId, Long patId);
}
