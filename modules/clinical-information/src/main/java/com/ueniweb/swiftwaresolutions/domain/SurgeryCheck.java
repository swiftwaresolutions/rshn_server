package com.ueniweb.swiftwaresolutions.domain;

import com.ueniweb.swiftwaresolutions.request.CreateNursingIoReuest;
import com.ueniweb.swiftwaresolutions.request.CreateOPVitalsRequest;
import com.ueniweb.swiftwaresolutions.request.CreateSurgeryChecklistReuest;
import com.ueniweb.swiftwaresolutions.utils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity(name="SurgeryCheck")
@Table(name = "cli_ot_surgery_checklist")
@Data
public class SurgeryCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patId", nullable = false)
    @Basic(optional = false)
    private Long patId;

    @Column(name = "visitId", nullable = false)
    @Basic(optional = false)
    private Long visitId;

    @Column(name = "ipId",nullable = false)
    @Basic(optional = false)
    private Long ipId;

    @Column(name = "airway")
    @Basic(optional = false)
    private Long airway;

    @Column(name = "allergy")
    @Basic(optional = false)
    private Long allergy;

    @Column(name = "anticipatedBlood")
    @Basic(optional = false)
    private Long anticipatedBlood;

    @Column(name = "bloodloss")
    @Basic(optional = false)
    private Long bloodloss;

    @Column(name = "confermation")
    @Basic(optional = false)
    private Long confermation;

    @Column(name = "critical")
    @Basic(optional = false)
    private Long critical;

    @Column(name = "equipmentIssues")
    @Basic(optional = false)
    private Long equipmentIssues;

    @Column(name = "equipmentProblems")
    @Basic(optional = false)
    private Long equipmentProblems;

    @Column(name = "imaging")
    @Basic(optional = false)
    private Long imaging;

    @Column(name = "incision")
    @Basic(optional = false)
    private Long incision;

    @Column(name = "marked")
    @Basic(optional = false)
    private Long marked;

    @Column(name = "medication")
    @Basic(optional = false)
    private Long medication;

    @Column(name = "oximeter")
    @Basic(optional = false)
    private Long oximeter;

    @Column(name = "patientSpecific")
    @Basic(optional = false)
    private Long patientSpecific;

    @Column(name = "procedures")
    @Basic(optional = false)
    private Long procedures;

    @Column(name = "prophylaxis")
    @Basic(optional = false)
    private Long prophylaxis;

    @Column(name = "recovery")
    @Basic(optional = false)
    private Long recovery;

    @Column(name = "specimen")
    @Basic(optional = false)
    private Long specimen;

    @Column(name = "sponge")
    @Basic(optional = false)
    private Long sponge;

    @Column(name = "sterility")
    @Basic(optional = false)
    private Long sterility;

    @Column(name = "teamMembers")
    @Basic(optional = false)
    private Long teamMembers;

    @Column(name = "timeTaken")
    @Basic(optional = false)
    private Long timeTaken;

    @Column(name = "dtm")
    @Basic(optional = false)
    private String dtm;

    @Column(name = "surgName")
    @Basic(optional = false)
    private String surgName;

    @Column(name = "docId")
    @Basic(optional = false)
    private Long docId;

    @Column(name = "oximeterIn")
    @Basic(optional = false)
    private String oximeterIn;

    @Column(name = "medicationIn")
    @Basic(optional = false)
    private String medicationIn;

    @Column(name = "prophylaxisIn")
    @Basic(optional = false)
    private String prophylaxisIn;

    @Column(name = "timeTakenIn")
    @Basic(optional = false)
    private String timeTakenIn;

    @Column(name = "anticipatedBloodIn")
    @Basic(optional = false)
    private String anticipatedBloodIn;

    @Column(name = "patientSpecificIn")
    @Basic(optional = false)
    private String patientSpecificIn;

    @Column(name = "proceduresIn")
    @Basic(optional = false)
    private String proceduresIn;

    @Column(name = "recoveryIn")
    @Basic(optional = false)
    private String recoveryIn;

    @Column(name = "docName")
    @Basic(optional = false)
    private String docName;

    public static SurgeryCheck to(final CreateSurgeryChecklistReuest createSurgeryChecklistReuest){

        SurgeryCheck surgeryCheck = new SurgeryCheck();
        surgeryCheck.setPatId(createSurgeryChecklistReuest.getPatId());
        surgeryCheck.setVisitId(createSurgeryChecklistReuest.getVisitId());
        surgeryCheck.setIpId(createSurgeryChecklistReuest.getIpId());
        surgeryCheck.setAirway(createSurgeryChecklistReuest.getAirway());
        surgeryCheck.setAllergy(createSurgeryChecklistReuest.getAllergy());
        surgeryCheck.setAnticipatedBlood(createSurgeryChecklistReuest.getAnticipatedBlood());
        surgeryCheck.setBloodloss(createSurgeryChecklistReuest.getBloodloss());
        surgeryCheck.setConfermation(createSurgeryChecklistReuest.getConfermation());
        surgeryCheck.setCritical(createSurgeryChecklistReuest.getCritical());
        surgeryCheck.setEquipmentIssues(createSurgeryChecklistReuest.getEquipmentIssues());
        surgeryCheck.setEquipmentProblems(createSurgeryChecklistReuest.getEquipmentProblems());
        surgeryCheck.setImaging(createSurgeryChecklistReuest.getImaging());
        surgeryCheck.setIncision(createSurgeryChecklistReuest.getIncision());
        surgeryCheck.setMarked(createSurgeryChecklistReuest.getMarked());
        surgeryCheck.setMedication(createSurgeryChecklistReuest.getMedication());
        surgeryCheck.setOximeter(createSurgeryChecklistReuest.getOximeter());
        surgeryCheck.setPatientSpecific(createSurgeryChecklistReuest.getPatientSpecific());
        surgeryCheck.setProcedures(createSurgeryChecklistReuest.getProcedures());
        surgeryCheck.setProphylaxis(createSurgeryChecklistReuest.getProphylaxis());
        surgeryCheck.setRecovery(createSurgeryChecklistReuest.getRecovery());
        surgeryCheck.setSpecimen(createSurgeryChecklistReuest.getSpecimen());
        surgeryCheck.setSponge(createSurgeryChecklistReuest.getSponge());
        surgeryCheck.setSterility(createSurgeryChecklistReuest.getSterility());
        surgeryCheck.setTeamMembers(createSurgeryChecklistReuest.getTeamMembers());
        surgeryCheck.setTimeTaken(createSurgeryChecklistReuest.getTimeTaken());
        surgeryCheck.setDtm(DateTimeUtils.convertLocalDateToDateTimeFormat(LocalDateTime.now()));
        surgeryCheck.setSurgName(createSurgeryChecklistReuest.getSurgName());
        surgeryCheck.setDocId(createSurgeryChecklistReuest.getDocId());
        surgeryCheck.setOximeterIn(createSurgeryChecklistReuest.getOximeterIn());
        surgeryCheck.setMedicationIn(createSurgeryChecklistReuest.getMedicationIn());
        surgeryCheck.setProphylaxisIn(createSurgeryChecklistReuest.getProphylaxisIn());
        surgeryCheck.setTimeTakenIn(createSurgeryChecklistReuest.getTimeTakenIn());
        surgeryCheck.setAnticipatedBloodIn(createSurgeryChecklistReuest.getAnticipatedBloodIn());
        surgeryCheck.setPatientSpecificIn(createSurgeryChecklistReuest.getPatientSpecificIn());
        surgeryCheck.setProceduresIn(createSurgeryChecklistReuest.getProceduresIn());
        surgeryCheck.setRecoveryIn(createSurgeryChecklistReuest.getRecoveryIn());
        surgeryCheck.setDocName(createSurgeryChecklistReuest.getDocName());



        return surgeryCheck;
    }
    public void update(final CreateSurgeryChecklistReuest createSurgeryChecklistReuest){

        this.setPatId(createSurgeryChecklistReuest.getPatId());
        this.setVisitId(createSurgeryChecklistReuest.getVisitId());
        this.setIpId(createSurgeryChecklistReuest.getIpId());
        this.setAirway(createSurgeryChecklistReuest.getAirway());
        this.setAllergy(createSurgeryChecklistReuest.getAllergy());
        this.setAnticipatedBlood(createSurgeryChecklistReuest.getAnticipatedBlood());
        this.setBloodloss(createSurgeryChecklistReuest.getBloodloss());
        this.setConfermation(createSurgeryChecklistReuest.getConfermation());
        this.setCritical(createSurgeryChecklistReuest.getCritical());
        this.setEquipmentIssues(createSurgeryChecklistReuest.getEquipmentIssues());
        this.setEquipmentProblems(createSurgeryChecklistReuest.getEquipmentProblems());
        this.setImaging(createSurgeryChecklistReuest.getImaging());
        this.setIncision(createSurgeryChecklistReuest.getIncision());
        this.setMarked(createSurgeryChecklistReuest.getMarked());
        this.setMedication(createSurgeryChecklistReuest.getMedication());
        this.setOximeter(createSurgeryChecklistReuest.getOximeter());
        this.setPatientSpecific(createSurgeryChecklistReuest.getPatientSpecific());
        this.setProcedures(createSurgeryChecklistReuest.getProcedures());
        this.setProphylaxis(createSurgeryChecklistReuest.getProphylaxis());
        this.setRecovery(createSurgeryChecklistReuest.getRecovery());
        this.setSpecimen(createSurgeryChecklistReuest.getSpecimen());
        this.setSponge(createSurgeryChecklistReuest.getSponge());
        this.setSterility(createSurgeryChecklistReuest.getSterility());
        this.setTeamMembers(createSurgeryChecklistReuest.getTeamMembers());
        this.setTimeTaken(createSurgeryChecklistReuest.getTimeTaken());
        this.setDtm(DateTimeUtils.convertLocalDateToDateTimeFormat(LocalDateTime.now()));
        this.setSurgName(createSurgeryChecklistReuest.getSurgName());
        this.setDocId(createSurgeryChecklistReuest.getDocId());
        this.setOximeterIn(createSurgeryChecklistReuest.getOximeterIn());
        this.setMedicationIn(createSurgeryChecklistReuest.getMedicationIn());
        this.setProphylaxisIn(createSurgeryChecklistReuest.getProphylaxisIn());
        this.setTimeTakenIn(createSurgeryChecklistReuest.getTimeTakenIn());
        this.setAnticipatedBloodIn(createSurgeryChecklistReuest.getAnticipatedBloodIn());
        this.setPatientSpecificIn(createSurgeryChecklistReuest.getPatientSpecificIn());
        this.setProceduresIn(createSurgeryChecklistReuest.getProceduresIn());
        this.setRecoveryIn(createSurgeryChecklistReuest.getRecoveryIn());
        this.setDocName(createSurgeryChecklistReuest.getDocName());
    }



}
