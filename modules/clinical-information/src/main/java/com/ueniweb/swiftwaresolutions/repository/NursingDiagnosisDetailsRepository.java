package com.ueniweb.swiftwaresolutions.repository;

import com.ueniweb.swiftwaresolutions.domain.NursingDiagnosisDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NursingDiagnosisDetailsRepository extends JpaRepository<NursingDiagnosisDetails, Long> {


	java.util.List<NursingDiagnosisDetails> findByCaseSheetTypeAndCaseSheetId(Long caseSheetType, Long caseSheetId);

}
