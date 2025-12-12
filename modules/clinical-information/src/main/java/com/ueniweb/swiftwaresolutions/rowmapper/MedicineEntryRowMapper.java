package com.ueniweb.swiftwaresolutions.rowmapper;

import com.ueniweb.swiftwaresolutions.data.MedicationFetchData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class MedicineEntryRowMapper implements RowMapper<MedicationFetchData.MedicineEntryData> {

    public String schema() {
        return " e.id AS entryId, " +
                " e.medicine_id AS medicineId, " +
                " m.name AS medicineName " +
                " FROM medicine_administration_entry e " +
                " JOIN ph_prods m ON m.id = e.medicine_id ";
    }

    @Override
    public MedicationFetchData.MedicineEntryData mapRow(ResultSet rs, int rowNum) throws SQLException {
        MedicationFetchData.MedicineEntryData data = new MedicationFetchData.MedicineEntryData();
        data.setEntryId(rs.getLong("entryId"));
        data.setMedicineId(rs.getLong("medicineId"));
        data.setMedicineName(rs.getString("medicineName"));
        return data;
    }
}

