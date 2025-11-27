package com.ueniweb.swiftwaresolutions.repository;

import com.ueniweb.swiftwaresolutions.domain.NursingCheckList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NursingCheckListRepository extends JpaRepository<NursingCheckList, Long> {

    Optional<NursingCheckList> findByVisitId(Integer visitId);
    Optional<NursingCheckList> findByVisitIdAndPatId(Integer visitId, Integer patId);
}