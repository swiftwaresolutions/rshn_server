package com.ueniweb.swiftwaresolutions.repository;

import com.ueniweb.swiftwaresolutions.domain.PrescriptionDetails;
import com.ueniweb.swiftwaresolutions.domain.PrescriptionManualMedDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrescriptionManualMedDetailsRepository
        extends JpaRepository<PrescriptionManualMedDetails, Long> {

    @Query("""
        select d
        from PrescriptionManualMedDetails d
        where d.prescriptionId.id = :prescriptionId
        """)
    List<PrescriptionManualMedDetails>
    fetchPrescriptionManualMedDetailsByPrescriptionId(
            @Param("prescriptionId")
            Long prescriptionId);
}
