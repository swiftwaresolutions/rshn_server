package com.ueniweb.swiftwaresolutions.rowmapper;
import com.ueniweb.swiftwaresolutions.data.IpPatientData;
import com.ueniweb.swiftwaresolutions.data.PatientData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IpPatientRowMapper implements RowMapper<IpPatientData> {

    private final String schema;


    public IpPatientRowMapper() {
        final StringBuilder tableBuilder = new StringBuilder(200);
        tableBuilder.append(" FROM `ip_visit`a,");
        tableBuilder.append("`rec_patient`b,");
        tableBuilder.append(" `rec_patient_opvisits` c ,");
        tableBuilder.append("`rec_config_msc_consultants`d,");
        tableBuilder.append(" `rec_config_msc_departments`e");

        this.schema = tableBuilder.toString();

    }


    public String schema() {
        return this.schema;
    }

    public String tableSchema() {

        final StringBuilder builder = new StringBuilder(200);

        builder.append("c.`id` AS visitId, ");
        builder.append("b.`name` as Name,");
        builder.append("a.`date`as date, ");
        builder.append("a.`time` as time, ");
        builder.append("d.`name` AS docName,");
        builder.append("e.`name` AS deptName");

        builder.append(this.schema );
        return builder.toString();
    }

    @Override
    public IpPatientData mapRow(ResultSet rs, int i) throws SQLException {
        final Long visitId = rs.getLong("visitId");
        final String name = rs.getString("Name");
        final String date = rs.getString("date");
        final String time = rs.getString("time");
        final String docName = rs.getString("docName");
        final String deptName = rs.getString("deptName");

        return IpPatientData.newInstance( visitId, name, date, time, docName, deptName);
    }

}
