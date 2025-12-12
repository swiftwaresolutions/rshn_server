package com.ueniweb.swiftwaresolutions.rowmapper;

import com.ueniweb.swiftwaresolutions.data.MedicationFetchData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class MedicationRowMapper implements RowMapper<MedicationFetchData> {

    public String schema() {
        return " ma.id AS id, " +
                " ma.entry_date AS entryDate, " +
                " ma.pat_id AS patientId, " +
                " ma.visit_id AS visitId, " +
                " ma.doctor_checked AS doctorChecked, " +
                " ma.consultant_id AS consultantId, " +
                " c.name AS consultantName " +
                " FROM medicine_administration ma "+
                " LEFT JOIN rec_config_msc_consultants c ON c.id = ma.consultant_id ";
    }

    @Override
    public MedicationFetchData mapRow(ResultSet rs, int rowNum) throws SQLException {
        MedicationFetchData data = new MedicationFetchData();
        data.setId(rs.getLong("id"));
        data.setEntryDate(rs.getString("entryDate"));
        data.setPatientId(rs.getLong("patientId"));
        data.setVisitId(rs.getLong("visitId"));
        data.setDoctorChecked(rs.getBoolean("doctorChecked"));
        data.setConsultantId(rs.getLong("consultantId"));
        data.setConsultantName(rs.getString("consultantName"));
        return data;
    }
}

