package com.ueniweb.swiftwaresolutions.repository;
import com.ueniweb.swiftwaresolutions.domain.OrderDiscount;
import com.ueniweb.swiftwaresolutions.domain.PrescTemplateDetails;
import com.ueniweb.swiftwaresolutions.domain.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    @Query("SELECT max(a.id) from Prescription a WHERE a.patId = :patId")
    String getPrescDisplay(@Param("patId") Long patId);

}
