package com.ueniweb.swiftwaresolutions.repository;

import com.ueniweb.swiftwaresolutions.domain.NursingAdmissionChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NursingAdmissionChartRepository extends JpaRepository<NursingAdmissionChart, Long> {
}
