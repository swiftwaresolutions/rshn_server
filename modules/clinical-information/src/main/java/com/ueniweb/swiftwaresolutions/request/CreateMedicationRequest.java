package com.ueniweb.swiftwaresolutions.request;

import lombok.Data;
import java.util.List;

@Data
public class CreateMedicationRequest {

    private String entryDate;
    private Long patientId;
    private Long visitId;
    private List<Long> medicineIds;
    private Long consultantId;
    private Boolean  doctorChecked;
    private Long nurseId;
    private List<List<MedicationTiming>> timings;

    @Data
    public static class MedicationTiming {
        private String time;
        private Long nurseId;
    }
}
