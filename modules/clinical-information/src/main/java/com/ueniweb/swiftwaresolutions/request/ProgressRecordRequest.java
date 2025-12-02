package com.ueniweb.swiftwaresolutions.request;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProgressRecordRequest {
    private Integer patId;
    private Integer visitId;
    private Integer ipId;
    private List<ProgressEntry> entries;

    @Getter
    @Setter
    public static class ProgressEntry {
        private Integer id;
        private LocalDate date;
        private String progressRecord;
    }
}