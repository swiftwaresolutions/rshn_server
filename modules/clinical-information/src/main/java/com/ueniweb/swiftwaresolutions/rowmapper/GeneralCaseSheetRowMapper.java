package com.ueniweb.swiftwaresolutions.rowmapper;

import com.ueniweb.swiftwaresolutions.data.GeneralCaseSheetData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneralCaseSheetRowMapper implements RowMapper<GeneralCaseSheetData> {

    private final String schema;

    private final String tableSchema;

    public GeneralCaseSheetRowMapper() {
        final StringBuilder tableBuilder = new StringBuilder(200);
        tableBuilder.append(" FROM cli_patient_general_casesheet cs ");

        final StringBuilder builder = new StringBuilder(200);
        builder.append("cs.id as id, ");
        builder.append("cs.patId as patId, ");
        builder.append("cs.vstId as vstId, ");
        builder.append("cs.ipId as ipId, ");
        builder.append("cs.presentIllness as presentIllness, ");
        builder.append("cs.pastHistory as pastHistory, ");
        builder.append("cs.treatmentHistory as treatmentHistory, ");
        builder.append("cs.personalHistory as personalHistory, ");
        builder.append("cs.investigationHistory as investigationHistory, ");
        builder.append("cs.menstrualHistory as menstrualHistory, ");
        builder.append("cs.temperature as temperature, ");
        builder.append("cs.pulse as pulse, ");
        builder.append("cs.rr as rr, ");
        builder.append("cs.bp as bp, ");
        builder.append("cs.spo2 as spo2, ");
        builder.append("cs.height as height, ");
        builder.append("cs.weight as weight, ");
        builder.append("cs.bmi as bmi, ");
        builder.append("cs.oralCavity as oralCavity, ");
        builder.append("cs.cvs as cvs, ");
        builder.append("cs.res as res, ");
        builder.append("cs.abdominal as abdominal, ");
        builder.append("cs.cns as cns, ");
        builder.append("cs.perVaginal as perVaginal, ");
        builder.append("cs.oralRectal as oralRectal, ");
        builder.append("cs.skin as skin, ");
        builder.append("cs.musculoskeletal as musculoskeletal, ");
        builder.append("cs.additionalFindings as additionalFindings, ");
        builder.append("cs.allergy as allergy, ");
        builder.append("cs.differentialDiagnosis as differentialDiagnosis, ");
        builder.append("cs.confirmedDiagnosis as confirmedDiagnosis, ");
        builder.append("cs.medications as medications, ");
        builder.append("cs.recomendations as recomendations, ");
        builder.append("cs.proceduresPlanned as proceduresPlanned, ");
        builder.append("cs.followUpPlan as followUpPlan,cs.examination,cs.diagnosis,cs.discussion ");
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
    public GeneralCaseSheetData mapRow(ResultSet rs, int i) throws SQLException {
        final Long id = rs.getLong("id");
        final Long patId = rs.getLong("patId");
        final Long vstId = rs.getLong("vstId");
        final Long ipId = rs.getLong("ipId");
        final String presentIllness = rs.getString("presentIllness");
        final String pastHistory = rs.getString("pastHistory");
        final String treatmentHistory = rs.getString("treatmentHistory");
        final String personalHistory = rs.getString("personalHistory");
        final String investigationHistory = rs.getString("investigationHistory");
        final String menstrualHistory = rs.getString("menstrualHistory");
        final String temperature = rs.getString("temperature");
        final String pulse = rs.getString("pulse");
        final String rr = rs.getString("rr");
        final String bp = rs.getString("bp");
        final String spo2 = rs.getString("spo2");
        final String height = rs.getString("height");
        final String weight = rs.getString("weight");
        final String bmi = rs.getString("bmi");
        final String oralCavity = rs.getString("oralCavity");
        final String cvs = rs.getString("cvs");
        final String res = rs.getString("res");
        final String abdominal = rs.getString("abdominal");
        final String cns = rs.getString("cns");
        final String perVaginal = rs.getString("perVaginal");
        final String oralRectal = rs.getString("oralRectal");
        final String skin = rs.getString("skin");
        final String musculoskeletal = rs.getString("musculoskeletal");
        final String additionalFindings = rs.getString("additionalFindings");
        final String allergy = rs.getString("allergy");
        final String differentialDiagnosis = rs.getString("differentialDiagnosis");
        final String confirmedDiagnosis = rs.getString("confirmedDiagnosis");
        final String medications = rs.getString("medications");
        final String recomendations = rs.getString("recomendations");
        final String proceduresPlanned = rs.getString("proceduresPlanned");
        final String followUpPlan = rs.getString("followUpPlan");
        final String examination = rs.getString("examination");
        final String diagnosis = rs.getString("diagnosis");
        final String discussion = rs.getString("discussion");
        return GeneralCaseSheetData.newInstance(id, patId, vstId, ipId, presentIllness,
                pastHistory, treatmentHistory, personalHistory, investigationHistory, menstrualHistory,
                temperature, pulse, rr, bp, spo2, height, weight, bmi, oralCavity, cvs,res, abdominal,
                cns, perVaginal, oralRectal, skin, musculoskeletal, additionalFindings,
                allergy, differentialDiagnosis, confirmedDiagnosis, medications, recomendations,
                proceduresPlanned, followUpPlan,examination,diagnosis,discussion);
    }
}