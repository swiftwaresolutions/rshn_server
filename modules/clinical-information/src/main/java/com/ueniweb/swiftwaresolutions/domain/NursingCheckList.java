package com.ueniweb.swiftwaresolutions.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import com.ueniweb.swiftwaresolutions.request.NursingCheckListReq;

@Data
@Entity
@Table(name = "cli_ot_nursing_checklist")
public class NursingCheckList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer patId;

    private Integer visitId;

    private Integer ipId;

    private Integer idBandWard;

    private Integer idBandOt;

    private Integer consentSignedWard;

    private Integer consentSignedOt;

    private Integer allergyWard;

    private String allergyDetailWard;

    private Integer allergyOt;

    private String allergyDetailOt;

    private String npoDetailWard;

    private String npoDetailOt;

    private String ivfORkvoWard;

    private String ivfORkvoOt;

    private Integer invDoneWard;

    private Integer invDoneOt;

    private Integer vitalCheckWard;

    private Integer vitalCheckOt;

    private Integer voidingPriorTransWard;

    private String voidingPriorTransTimeWard;

    private Integer voidingPriorTransOt;

    private String voidingPriorTransTimeOt;

    private Integer prosthesisRemovedWard;

    private Integer prosthesisRemovedOt;

    private Integer patItemsRemovedWard;

    private Integer patItemsRemovedOt;

    private Integer regularMedWard;

    private String regularMedDetailWard;

    private Integer regularMedOt;

    private String regularMedDetailOt;

    private Integer preMedicationWard;

    private Integer preMedicationOt;

    private Integer skinPreparationWard;

    private Integer skinPreparationOt;

    private Double weightWard;

    private Double weightOt;

    private Integer photosWard;

    private Integer photosOt;

    private String nurseWard;

    private String nurseOt;

    private LocalDate entDate;

    private LocalTime entTime;

    private Long entUser;

    private LocalDate editDate;

    private LocalTime editTime;

    private Long editUser;

    public static NursingCheckList save(final NursingCheckListReq req, Long userId) {
        NursingCheckList nursingCheckList = new NursingCheckList();
        nursingCheckList.setPatId(req.getPatId());
        nursingCheckList.setVisitId(req.getVisitId());
        nursingCheckList.setIpId(req.getIpId());

        nursingCheckList.setIdBandWard(req.getIdBandWard());
        nursingCheckList.setIdBandOt(req.getIdBandOt());
        nursingCheckList.setConsentSignedWard(req.getConsentSignedWard());
        nursingCheckList.setConsentSignedOt(req.getConsentSignedOt());
        nursingCheckList.setAllergyWard(req.getAllergyWard());
        nursingCheckList.setAllergyDetailWard(req.getAllergyDetailWard());
        nursingCheckList.setAllergyOt(req.getAllergyOt());
        nursingCheckList.setAllergyDetailOt(req.getAllergyDetailOt());
        nursingCheckList.setNpoDetailWard(req.getNpoDetailWard());
        nursingCheckList.setNpoDetailOt(req.getNpoDetailOt());
        nursingCheckList.setIvfORkvoWard(req.getIvfORkvoWard());
        nursingCheckList.setIvfORkvoOt(req.getIvfORkvoOt());
        nursingCheckList.setInvDoneWard(req.getInvDoneWard());
        nursingCheckList.setInvDoneOt(req.getInvDoneOt());
        nursingCheckList.setVitalCheckWard(req.getVitalCheckWard());
        nursingCheckList.setVitalCheckOt(req.getVitalCheckOt());
        nursingCheckList.setVoidingPriorTransWard(req.getVoidingPriorTransWard());
        nursingCheckList.setVoidingPriorTransTimeWard(req.getVoidingPriorTransTimeWard());
        nursingCheckList.setVoidingPriorTransOt(req.getVoidingPriorTransOt());
        nursingCheckList.setVoidingPriorTransTimeOt(req.getVoidingPriorTransTimeOt());
        nursingCheckList.setProsthesisRemovedWard(req.getProsthesisRemovedWard());
        nursingCheckList.setProsthesisRemovedOt(req.getProsthesisRemovedOt());
        nursingCheckList.setPatItemsRemovedWard(req.getPatItemsRemovedWard());
        nursingCheckList.setPatItemsRemovedOt(req.getPatItemsRemovedOt());
        nursingCheckList.setRegularMedWard(req.getRegularMedWard());
        nursingCheckList.setRegularMedDetailWard(req.getRegularMedDetailWard());
        nursingCheckList.setRegularMedOt(req.getRegularMedOt());
        nursingCheckList.setRegularMedDetailOt(req.getRegularMedDetailOt());
        nursingCheckList.setPreMedicationWard(req.getPreMedicationWard());
        nursingCheckList.setPreMedicationOt(req.getPreMedicationOt());
        nursingCheckList.setSkinPreparationWard(req.getSkinPreparationWard());
        nursingCheckList.setSkinPreparationOt(req.getSkinPreparationOt());
        nursingCheckList.setWeightWard(req.getWeightWard());
        nursingCheckList.setWeightOt(req.getWeightOt());
        nursingCheckList.setPhotosWard(req.getPhotosWard());
        nursingCheckList.setPhotosOt(req.getPhotosOt());
        nursingCheckList.setNurseWard(req.getNurseWard());
        nursingCheckList.setNurseOt(req.getNurseOt());

        nursingCheckList.setEntDate(LocalDate.now());
        nursingCheckList.setEntTime(LocalTime.now());
        nursingCheckList.setEntUser(userId);

        nursingCheckList.setEditDate(LocalDate.now());
        nursingCheckList.setEditTime(LocalTime.now());
        nursingCheckList.setEditUser(userId);

        return nursingCheckList;
    }

    public void update(final NursingCheckListReq req, Long userId) {
        this.setPatId(req.getPatId());
        this.setVisitId(req.getVisitId());
        this.setIpId(req.getIpId());

        this.setIdBandWard(req.getIdBandWard());
        this.setIdBandOt(req.getIdBandOt());
        this.setConsentSignedWard(req.getConsentSignedWard());
        this.setConsentSignedOt(req.getConsentSignedOt());
        this.setAllergyWard(req.getAllergyWard());
        this.setAllergyDetailWard(req.getAllergyDetailWard());
        this.setAllergyOt(req.getAllergyOt());
        this.setAllergyDetailOt(req.getAllergyDetailOt());
        this.setNpoDetailWard(req.getNpoDetailWard());
        this.setNpoDetailOt(req.getNpoDetailOt());
        this.setIvfORkvoWard(req.getIvfORkvoWard());
        this.setIvfORkvoOt(req.getIvfORkvoOt());
        this.setInvDoneWard(req.getInvDoneWard());
        this.setInvDoneOt(req.getInvDoneOt());
        this.setVitalCheckWard(req.getVitalCheckWard());
        this.setVitalCheckOt(req.getVitalCheckOt());
        this.setVoidingPriorTransWard(req.getVoidingPriorTransWard());
        this.setVoidingPriorTransTimeWard(req.getVoidingPriorTransTimeWard());
        this.setVoidingPriorTransOt(req.getVoidingPriorTransOt());
        this.setVoidingPriorTransTimeOt(req.getVoidingPriorTransTimeOt());
        this.setProsthesisRemovedWard(req.getProsthesisRemovedWard());
        this.setProsthesisRemovedOt(req.getProsthesisRemovedOt());
        this.setPatItemsRemovedWard(req.getPatItemsRemovedWard());
        this.setPatItemsRemovedOt(req.getPatItemsRemovedOt());
        this.setRegularMedWard(req.getRegularMedWard());
        this.setRegularMedDetailWard(req.getRegularMedDetailWard());
        this.setRegularMedOt(req.getRegularMedOt());
        this.setRegularMedDetailOt(req.getRegularMedDetailOt());
        this.setPreMedicationWard(req.getPreMedicationWard());
        this.setPreMedicationOt(req.getPreMedicationOt());
        this.setSkinPreparationWard(req.getSkinPreparationWard());
        this.setSkinPreparationOt(req.getSkinPreparationOt());
        this.setWeightWard(req.getWeightWard());
        this.setWeightOt(req.getWeightOt());
        this.setPhotosWard(req.getPhotosWard());
        this.setPhotosOt(req.getPhotosOt());
        this.setNurseWard(req.getNurseWard());
        this.setNurseOt(req.getNurseOt());

        this.setEditDate(LocalDate.now());
        this.setEditTime(LocalTime.now());
        this.setEditUser(userId);
    }
}