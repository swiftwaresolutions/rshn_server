package com.ueniweb.swiftwaresolutions.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "medicine_administration")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entry_date")
    private LocalDate entryDate;

    @Column(name = "pat_id")
    private Long patientId;

    @Column(name = "visit_id")
    private Long visitId;

    @Column(name = "doctor_checked")
    private Boolean doctorChecked;

    @Column(name = "consultant_id")
    private Long consultantId;

    @Column(name = "uid")
    private Long nurseId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
