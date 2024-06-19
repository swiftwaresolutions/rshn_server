package com.ueniweb.swiftwaresolutions.repository;

import com.ueniweb.swiftwaresolutions.domain.PatientOpVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientOpVisitRepository extends JpaRepository<PatientOpVisit, Long> {

    @Query("SELECT pv from PatientOpVisit pv WHERE pv.patientId = :patientId ORDER BY pv.id DESC")
    List<PatientOpVisit> findPatientOpVisitByPatientId(@Param("patientId") Long patientId);

}
