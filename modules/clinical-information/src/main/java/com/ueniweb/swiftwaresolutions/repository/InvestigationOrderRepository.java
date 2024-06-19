package com.ueniweb.swiftwaresolutions.repository;

import com.ueniweb.swiftwaresolutions.domain.InvestigationOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestigationOrderRepository extends JpaRepository<InvestigationOrder, Long>  {

    @Query("SELECT l from InvestigationOrder l WHERE l.patId = :patientId")
    List<InvestigationOrder> findInvestigationOrderByPatientId(@Param("patientId") Long patientId);
}
