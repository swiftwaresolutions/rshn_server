package com.ueniweb.swiftwaresolutions.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
@Table(name = "medicine_administration_timing")
public class MedicineAdministrationTiming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entry_id")
    private Long entryId;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "nurse_id")
    private Long nurseId;
}
