package com.ueniweb.swiftwaresolutions.request;

import lombok.Data;

@Data
public class NursingCheckListReq {

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

}

