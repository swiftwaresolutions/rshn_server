package com.ueniweb.swiftwaresolutions.domain;

import com.ueniweb.swiftwaresolutions.request.CreateManualMedPrescriptionDetailsRequest;
import com.ueniweb.swiftwaresolutions.request.CreatePrescriptionDetailsRequest;
import com.ueniweb.swiftwaresolutions.utils.DateTimeUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "PrescriptionManualMedDetails")
@Table(name = "ph_prescription_details_manual_med")
@Getter
@Setter
public class PrescriptionManualMedDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "prescription_id", nullable = false)
    private Prescription prescriptionId;

    @Column(name = "med_name")
    private String medName;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "unit")
    private Long unit;

    @Column(name = "timing")
    private Long timing;

    @Column(name = "is_own")
    private Long isOwn;

    @Column(name = "is_cancelled")
    private Long isCancelled;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    public static List<PrescriptionManualMedDetails> to(
            final Prescription prescription,
            final List<CreateManualMedPrescriptionDetailsRequest> requestList) {

        List<PrescriptionManualMedDetails> list = new ArrayList<>();

        if (requestList == null) {
            return list;
        }

        for (CreateManualMedPrescriptionDetailsRequest request : requestList) {
            list.add(to(prescription, request));
        }

        return list;
    }

    public static PrescriptionManualMedDetails to(
            final Prescription prescription,
            final CreateManualMedPrescriptionDetailsRequest request) {

        PrescriptionManualMedDetails detail =
                new PrescriptionManualMedDetails();

        detail.setPrescriptionId(prescription);
        detail.setMedName(request.getMed_name());
        detail.setQuantity(request.getQuantity());
        detail.setUnit(request.getUnit());
        detail.setTiming(request.getTiming());
        detail.setIsOwn(request.getIs_own());
        detail.setIsCancelled(request.getIs_cancelled());
        detail.setNotes(request.getNotes());

        return detail;
    }
}