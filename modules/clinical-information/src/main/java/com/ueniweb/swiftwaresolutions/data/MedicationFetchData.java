package com.ueniweb.swiftwaresolutions.data;

import lombok.Data;
import java.util.List;

@Data
public class MedicationFetchData {

    private Long id;
    private String entryDate;
    private Long patientId;
    private Long visitId;
    private Boolean doctorChecked;
    private Long consultantId;
    private String consultantName;

    private List<MedicineEntryData> medicineEntries;

    @Data
    public static class MedicineEntryData {
        private Long entryId;
        private Long medicineId;
        private String medicineName;
        private List<TimingData> timings;
    }

    @Data
    public static class TimingData {
        private String time;
        private Long nurseId;
        private String nurseName;
    }
}
