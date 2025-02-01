package com.ueniweb.swiftwaresolutions.data;

import jakarta.persistence.*;
import lombok.Data;
@Data
public class SurgeryCheckListData {

    private Long id;

    private Long patId;

    private Long visitId;

    private Long ipId;

    private Long airway;

    private Long allergy;

    private Long anticipatedBlood;

    private Long bloodloss;

    private Long confermation;

    private Long critical;

    private Long equipmentIssues;

    private Long equipmentProblems;

    private Long imaging;

    private Long incision;

    private Long marked;

    private Long medication;

    private Long oximeter;

    private Long patientSpecific;

    private Long procedures;

    private Long prophylaxis;

    private Long recovery;

    private Long specimen;

    private Long sponge;

    private Long sterility;

    private Long teamMembers;

    private Long timeTaken;

    private String dtm;

    private String surgName;

    private Long docId;

    private String oximeterIn;

    private String medicationIn;

    private String prophylaxisIn;

    private String timeTakenIn;

    private String anticipatedBloodIn;

    private String patientSpecificIn;

    private String proceduresIn;

    private String recoveryIn;

    private String docName;

    public SurgeryCheckListData(final Long id,    final Long patId,    final Long visitId,    final Long ipId,    final Long airway,    final Long allergy,    final Long anticipatedBlood,    final Long bloodloss,
                                final Long confermation,    final Long critical,    final Long equipmentIssues,    final Long equipmentProblems,    final Long imaging,    final Long incision,    final Long marked,
                                final Long medication,    final Long oximeter,    final Long patientSpecific,    final Long procedures,    final Long prophylaxis,    final Long recovery,    final Long specimen,
                                final Long sponge,    final Long sterility,    final Long teamMembers,    final Long timeTaken,    final String dtm,final String surgName ,final Long docId, final String oximeterIn,
                                final String medicationIn, final String prophylaxisIn, final String timeTakenIn, final String anticipatedBloodIn,
                                final String patientSpecificIn, final String proceduresIn, final String recoveryIn  ,final String docName){

        this.id=id;

        this.patId=patId;

        this.visitId=visitId;

        this.ipId=ipId;

        this.airway=airway;

        this.allergy=allergy;

        this.anticipatedBlood=anticipatedBlood;

        this.bloodloss=bloodloss;

        this.confermation=confermation;

        this.critical=critical;

        this.equipmentIssues=equipmentIssues;

        this.equipmentProblems=equipmentProblems;

        this.imaging=imaging;

        this.incision=incision;

        this.marked=marked;

        this.medication=medication;

        this.oximeter=oximeter;

        this.patientSpecific=patientSpecific;

        this.procedures=procedures;

        this.prophylaxis=prophylaxis;

        this.recovery=recovery;

        this.specimen=specimen;

        this.sponge=sponge;

        this.sterility=sterility;

        this.teamMembers=teamMembers;

        this.timeTaken=timeTaken;

        this.dtm=dtm;

        this.surgName=surgName;

        this.docId=docId;

        this.oximeterIn=oximeterIn;

        this.medicationIn=medicationIn;

        this.prophylaxisIn=prophylaxisIn;

        this.timeTakenIn=timeTakenIn;

        this.anticipatedBloodIn=anticipatedBloodIn;

        this.patientSpecificIn=patientSpecificIn;

        this.proceduresIn=proceduresIn;

        this.recoveryIn=recoveryIn;

        this.docName=docName;

    }
    public  static SurgeryCheckListData createNewInstance(final Long id,    final Long patId,    final Long visitId,    final Long ipId,    final Long airway,    final Long allergy,    final Long anticipatedBlood,    final Long bloodloss,
                                                          final Long confermation,    final Long critical,    final Long equipmentIssues,    final Long equipmentProblems,    final Long imaging,    final Long incision,    final Long marked,
                                                          final Long medication,    final Long oximeter,    final Long patientSpecific,    final Long procedures,    final Long prophylaxis,    final Long recovery,    final Long specimen,
                                                          final Long sponge,    final Long sterility,    final Long teamMembers,    final Long timeTaken,    final String dtm,final String surgName ,final Long docId, final String oximeterIn,
                                                          final String medicationIn, final String prophylaxisIn, final String timeTakenIn, final String anticipatedBloodIn,
                                                          final String patientSpecificIn, final String proceduresIn, final String recoveryIn ,final String docName){
        return new SurgeryCheckListData( id,     patId,     visitId,     ipId,     airway,     allergy,     anticipatedBlood,     bloodloss,     confermation,     critical,     equipmentIssues,     equipmentProblems,     imaging,     incision,     marked,     medication,     oximeter,     patientSpecific,     procedures,     prophylaxis,     recovery,     specimen,     sponge,     sterility,     teamMembers,     timeTaken,     dtm,surgName,docId,oximeterIn,
        medicationIn, prophylaxisIn, timeTakenIn, anticipatedBloodIn,
        patientSpecificIn, proceduresIn, recoveryIn ,docName);
    }

}
