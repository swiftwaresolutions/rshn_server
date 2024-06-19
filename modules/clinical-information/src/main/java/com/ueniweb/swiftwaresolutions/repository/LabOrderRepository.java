package com.ueniweb.swiftwaresolutions.repository;

import com.ueniweb.swiftwaresolutions.domain.LabOrder;
import com.ueniweb.swiftwaresolutions.domain.PatientOpVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabOrderRepository extends JpaRepository<LabOrder, Long> {

    @Query("SELECT l from LabOrder l WHERE l.patId = :patientId")
    List<LabOrder> findLabByPatientId(@Param("patientId") Long patientId);
}
