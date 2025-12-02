package com.ueniweb.swiftwaresolutions.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Data
@Entity
@Table(name = "cli_progress_record")
@Getter
@Setter
public class ProgressRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "patId", nullable = false)
    private Integer patId;

    @Column(name = "visitId", nullable = false)
    private Integer visitId;

    @Column(name = "ipId", nullable = false)
    private Integer ipId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "progressRecord", columnDefinition = "TEXT")
    private String progressRecord;

    @Column(name = "entryDateTime")
    private LocalDateTime entryDateTime;

    @Column(name = "entryUserId")
    private Integer entryUserId;

    @Column(name = "editDateTime")
    private LocalDateTime editDateTime;

    @Column(name = "editUserId")
    private Integer editUserId;


}
