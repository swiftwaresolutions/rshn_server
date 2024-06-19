package com.ueniweb.swiftwaresolutions.repository;

import com.ueniweb.swiftwaresolutions.domain.PediatricComplaintDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PediatricComplaintDetailsRepository extends JpaRepository<PediatricComplaintDetails, Long> {
    @Query("select d from ComplaintDetails d where d.caseSheetId.id=:caseSheetId AND d.caseSheetType=:caseSheetType")
    List<PediatricComplaintDetails> fetchComplaintDetailsBycaseSheetId(@Param("caseSheetId") Long caseSheetId,
                                                              @Param("caseSheetType") Integer caseSheetType);

}
