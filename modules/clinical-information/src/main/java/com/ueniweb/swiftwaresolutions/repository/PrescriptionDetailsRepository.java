package com.ueniweb.swiftwaresolutions.repository;

import com.ueniweb.swiftwaresolutions.domain.PrescriptionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrescriptionDetailsRepository extends JpaRepository<PrescriptionDetails, Long> {

    @Query("select d from PrescriptionDetails d where d.prescriptionId.id=:prescriptionId")
    List<PrescriptionDetails> fetchPrescriptionDetailsByPrescriptionId(@Param("prescriptionId") Long prescriptionId);

}
