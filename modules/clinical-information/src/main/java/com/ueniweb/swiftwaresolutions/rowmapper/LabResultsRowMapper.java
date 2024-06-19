package com.ueniweb.swiftwaresolutions.rowmapper;

import com.ueniweb.swiftwaresolutions.data.LabResultData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LabResultsRowMapper implements RowMapper<LabResultData> {

    private final String schema;

    public LabResultsRowMapper(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("FROM lab_config_master_test a,lab_config_master_specimen b,lab_test_reg c,lab_reg_dept_regno d," +
                "lab_reg_maj_regno e,lab_reg_data_pat f,lab_test_value g,lab_config_master_test_fld h,lab_reg_test_value i ");
        this.schema = builder.toString();
    }


    public String schema() {
        return this.schema;
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("DATE_FORMAT(e.ent_date,'%d-%m-%Y') AS date, ");
        builder.append("b.spec_name AS specName, ");
        builder.append("a.test_name AS testName, ");
        builder.append("g.value AS value, ");
        builder.append("h.unit AS unit, ");
        builder.append("h.fieldname AS fieldName, ");
        builder.append("e.normalpat_id AS normalPatId, ");
        builder.append("d.dept_autoid AS deptAutoId, ");
        builder.append("g.field_id AS fieldId ");
        builder.append(this.schema);
        return builder.toString();
        }

    @Override
    public LabResultData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final String date = rs.getString("date");
        final String specName = rs.getString("specName");
        final String testName = rs.getString("testName");
        final String value = rs.getString("value");
        final String unit = rs.getString("unit");
        final String fieldName = rs.getString("fieldName");
        final Long normalPatId = rs.getLong("normalPatId");
        final Long deptAutoId = rs.getLong("deptAutoId");
        final Long fieldId = rs.getLong("fieldId");
        return LabResultData.createNewInstance(date, specName, testName, value, unit, fieldName, normalPatId, deptAutoId, fieldId);
    }
}
