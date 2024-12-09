package com.ueniweb.swiftwaresolutions.rowmapper;

import com.ueniweb.swiftwaresolutions.data.PatientData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrevVisitPatientRowMapper implements RowMapper<PatientData> {

    private final String schema;

    private final String tableSchema;

    public PrevVisitPatientRowMapper() {
        final StringBuilder tableBuilder = new StringBuilder(200);

        tableBuilder.append(" FROM rec_patient a,  ");
        tableBuilder.append(" rec_patient_opvisits b ");
        tableBuilder.append(" LEFT OUTER JOIN rec_patient_details p ON p.pat_id = b.pat_id ");
        tableBuilder.append(" LEFT OUTER JOIN rec_config_msc_consultants d ON d.id = b.doctor_id ");
        tableBuilder.append(" LEFT OUTER JOIN rec_config_msc_departments dep ON dep.id = b.`service_id` ");

        final StringBuilder builder = new StringBuilder(200);

        builder.append("d.name as name, ");
        builder.append("a.display_number as displayNumber, ");
        builder.append("a.name as name, ");
        builder.append("CONCAT(p.name1, ' ', p.name2) AS full_name, ");
        builder.append("calcAge(p.dob) AS age, ");
        builder.append("IF(p.sex = 'M', 'Male', 'Female') AS gender, ");
        builder.append("b.token_no_doctor, ");
        builder.append("b.date AS date, ");
        builder.append("d.id AS doctor_id, ");
        builder.append("a.id AS patient_id, ");
        builder.append("b.id AS visit_id, ");
        builder.append("dep.name AS departmentName \n");

        builder.append(tableBuilder);
        this.tableSchema = tableBuilder.toString();
        this.schema = builder.toString();
    }
    public String schema() {
        return this.schema;
    }

    public String tableSchema() {
        return this.tableSchema;
    }

    @Override
    public PatientData mapRow(ResultSet rs, int i) throws SQLException {
        final String name = rs.getString("name");
        final String displayNumber = rs.getString("displayNumber");
        final String fullName = rs.getString("full_name");
        final String age = rs.getString("age");
        final String gender = rs.getString("gender");
        final String tokenNoDoctor = rs.getString("token_no_doctor");
        final String formattedTime = rs.getString("date");
        final Long doctorId = rs.getLong("doctor_id");
        final Long patientId = rs.getLong("patient_id");
        final Long visitId = rs.getLong("visit_id");
        final String departmentName = rs.getString("departmentName");
        final boolean isNew = false;
        final int casesheetStatus = 0;
        final int labStatus = 0;
        final int procStatus = 0;
        final int prescriptionStatus = 0;
        final int vitalStatus = 0;

        return PatientData.newInstance(name, displayNumber, fullName, age, gender, tokenNoDoctor, formattedTime,
                doctorId, patientId, visitId, isNew, casesheetStatus, labStatus, procStatus,
                prescriptionStatus, departmentName,vitalStatus);
    }
}
