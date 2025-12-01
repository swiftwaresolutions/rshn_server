package com.ueniweb.swiftwaresolutions.rowmapper;

import com.ueniweb.swiftwaresolutions.data.NursingAdmissionChartData;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NursingAdmissionChartRowMapper implements RowMapper<NursingAdmissionChartData> {

    public static final String BASE_QUERY = """
        SELECT 
            nac.id, nac.patId, nac.visitId, nac.ipId, nac.doctorId, 
            nac.historyByDoctor, nac.historyOfAllergy, nac.bloodGroup, 
            nac.provisionalDiagnosis, nac.dischargeDiagnosis, nac.dateTime, 
            nac.department, nac.medication, nac.dischargeCondition, 
            nac.invProcedures, nac.entryDateTime, nac.entryUserId, 
            nac.editDateTime, nac.editUserId
        FROM cli_nursing_admission_chart nac
        """;

    @Override
    public NursingAdmissionChartData mapRow(ResultSet rs, int rowNum) throws SQLException {
        NursingAdmissionChartData data = new NursingAdmissionChartData();
        data.setId(rs.getLong("id"));
        data.setPatId(rs.getLong("patId"));
        data.setVisitId(rs.getLong("visitId"));
        data.setIpId(rs.getLong("ipId"));
        data.setDoctorId(rs.getLong("doctorId"));
        data.setHistoryByDoctor(rs.getString("historyByDoctor"));
        data.setHistoryOfAllergy(rs.getString("historyOfAllergy"));
        data.setBloodGroup(rs.getString("bloodGroup"));
        data.setProvisionalDiagnosis(rs.getString("provisionalDiagnosis"));
        data.setDischargeDiagnosis(rs.getString("dischargeDiagnosis"));
        data.setDateTime(rs.getString("dateTime"));
        data.setDepartment(rs.getString("department"));
        data.setMedication(rs.getString("medication"));
        data.setDischargeCondition(rs.getString("dischargeCondition"));
        data.setInvProcedures(rs.getString("invProcedures"));
        data.setEntryDateTime(rs.getString("entryDateTime"));
        data.setEntryUserId(rs.getLong("entryUserId"));
        data.setEditDateTime(rs.getString("editDateTime"));
        data.setEditUserId(rs.getLong("editUserId"));
        return data;
    }
}