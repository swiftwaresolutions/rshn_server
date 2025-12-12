package com.ueniweb.swiftwaresolutions.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "medicine_administration_entry")
public class MedicineAdministrationEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "administration_id")
    private Long administrationId;

    @Column(name = "medicine_id")
    private Long medicineId;
}
