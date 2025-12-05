package com.ueniweb.swiftwaresolutions.repository;

import com.ueniweb.swiftwaresolutions.domain.NursingChartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NursingChartDetailRepository extends JpaRepository<NursingChartDetail, Long> {

    List<NursingChartDetail> findByChartId(Long chartId);
    
    List<NursingChartDetail> findByChartIdAndIsValid(Long chartId, Integer isValid);
}
