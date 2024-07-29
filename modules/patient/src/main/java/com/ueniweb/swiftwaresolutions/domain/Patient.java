package com.ueniweb.swiftwaresolutions.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "RecPatient")
@Table(name = "rec_patient")
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "display_number", nullable = false)
    @Basic(optional = false)
    private String displayNumber;

}
