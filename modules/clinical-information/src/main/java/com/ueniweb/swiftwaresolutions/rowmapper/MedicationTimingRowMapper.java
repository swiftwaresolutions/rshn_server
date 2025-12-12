package com.ueniweb.swiftwaresolutions.rowmapper;

import com.ueniweb.swiftwaresolutions.data.MedicationFetchData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class MedicationTimingRowMapper implements RowMapper<MedicationFetchData.TimingData> {

    public String schema() {
        return " t.time AS time, " +
                " t.nurse_id AS nurseId, " +
                " u.full_name AS nurseName " +
                " FROM medicine_administration_timing t " +
                " JOIN admin_users u ON u.id = t.nurse_id ";
    }

    @Override
    public MedicationFetchData.TimingData mapRow(ResultSet rs, int rowNum) throws SQLException {
        MedicationFetchData.TimingData data = new MedicationFetchData.TimingData();
        data.setTime(rs.getString("time"));
        data.setNurseId(rs.getLong("nurseId"));
        data.setNurseName(rs.getString("nurseName"));
        return data;
    }
}
