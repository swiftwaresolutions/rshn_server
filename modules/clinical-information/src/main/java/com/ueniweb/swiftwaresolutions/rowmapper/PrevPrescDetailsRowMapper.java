package com.ueniweb.swiftwaresolutions.rowmapper;
import com.ueniweb.swiftwaresolutions.data.PrevPrescriptionDetailsData;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class PrevPrescDetailsRowMapper  implements RowMapper<PrevPrescriptionDetailsData> {

    private final String schema;

    public PrevPrescDetailsRowMapper(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append("FROM ph_prescription_details a " );
        builder.append("LEFT OUTER JOIN ph_prods_unit h ON a.unit=h.id ");
        builder.append("LEFT OUTER JOIN nur_master_timing i ON a.timing=i.id ");
        builder.append("LEFT OUTER JOIN ph_config_duration j ON a.period=j.id ");
        builder.append("LEFT OUTER JOIN ph_config_instruction k ON a.instruction=k.id ");
        builder.append("LEFT OUTER JOIN ph_config_route l ON a.route=l.id,ph_prescription b,ph_generic d,ph_prods e ");
        builder.append("LEFT OUTER JOIN ph_prods_forms m ON e.`form_id`=m.`id` ");
        this.schema = builder.toString();
    }


    public String schema() {
        return this.schema;
    }

    public String tableSchema(Integer storeId) {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("b.display as displayNo,d.id AS genId,d.`name` AS genName,e.`id`,e.`name` AS medName,m.`form_type` AS formType,h.`name` AS unit,h.`id` AS unitId,\n" +
                " e.`strength` AS medStrength,e.`quantity` AS medQuantity,getStoreStock(e.id,"+storeId+")  AS stock,i.`name`AS timing,i.id AS timingId,a.`quantity`,a.`duration`,j.`name`AS period,\n" +
                " j.`id`AS periodId,a.`qno` as no,ifnull(a.timingUnits,'0-0-0') as timingUnit,getBatchMrpRate(e.`name`) as mrpPrice,a.is_own as own ,a.date AS DATE,b.isFromSummary,b.is_billed ");
        builder.append(this.schema);
        return builder.toString();
    }

    public String tableSchemaByVstId(Integer storeId, Long vstId, Integer isFromSummary) {
        final StringBuilder builder = new StringBuilder();

        builder.append(" SELECT ");
        builder.append("b.display as displayNo,d.id AS genId,d.`name` AS genName,e.`id`,e.`name` AS medName,m.`form_type` AS formType,h.`name` AS unit,h.`id` AS unitId,");
        builder.append(" e.`strength` AS medStrength,e.`quantity` AS medQuantity,getStoreStock(e.id,").append(storeId).append(") AS stock,i.`name` AS timing,i.id AS timingId,a.`quantity`,a.`duration`,j.`name` AS period,");
        builder.append(" j.`id` AS periodId,a.`qno` AS no,ifnull(a.timingUnits,'0-0-0') AS timingUnit,getBatchMrpRate(e.`name`) AS mrpPrice,a.is_own AS own,a.date AS date,b.isFromSummary,b.is_billed ");
        builder.append(this.schema);
        builder.append(" WHERE a.generic_id=d.id AND a.prods_id=e.id AND a.prescription_id=b.id AND b.visit_id=").append(vstId);
        builder.append(" AND b.isFromSummary=").append(isFromSummary).append(" AND a.is_cancelled=0 ");

        builder.append(" UNION ALL ");
        builder.append(" SELECT ");
        builder.append(" b.display AS displayNo,");
        builder.append(" 0 AS genId,");
        builder.append(" '' AS genName,");
        builder.append(" a.id,");
        builder.append(" a.med_name AS medName,");
        builder.append(" 0 AS formType,");
        builder.append(" h.name AS unit,");
        builder.append(" h.id AS unitId,");
        builder.append(" 0 AS medStrength,");
        builder.append(" 0 AS medQuantity,");
        builder.append(" 0 AS stock,");
        builder.append(" i.name AS timing,");
        builder.append(" i.id AS timingId,");
        builder.append(" a.quantity,");
        builder.append(" 0 AS duration,");
        builder.append(" '' AS period,");
        builder.append(" 0 AS periodId,");
        builder.append(" a.quantity AS no,");
        builder.append(" '0-0-0' AS timingUnit,");
        builder.append(" 0 AS mrpPrice,");
        builder.append(" a.is_own AS own,");
        builder.append(" '' AS date,");
        builder.append(" b.isFromSummary,");
        builder.append(" b.is_billed ");
        builder.append(" FROM ph_prescription_details_manual_med a ");
        builder.append(" INNER JOIN ph_prescription b ON a.prescription_id=b.id ");
        builder.append(" LEFT JOIN ph_prods_unit h ON a.unit=h.id ");
        builder.append(" LEFT JOIN nur_master_timing i ON a.timing=i.id ");
        builder.append(" WHERE b.visit_id=").append(vstId);
        builder.append(" AND b.isFromSummary=").append(isFromSummary);
        builder.append(" AND a.is_cancelled=0 ORDER BY displayNo");

        return builder.toString();
    }

    @Override
    public PrevPrescriptionDetailsData mapRow(ResultSet rs, int rowNum) throws SQLException {

        final String displayNo        = rs.getString("displayNo");
        final Long genId            = rs.getLong("genId");
        final String genName        = rs.getString("genName");
        final Long id               = rs.getLong("id");
        final String medName        = rs.getString("medName");
        final Long formType         = rs.getLong("formType");
        final String unit           = rs.getString("unit");
        final Long unitId           = rs.getLong("unitId");
        final Integer medStrength   = rs.getInt("medStrength");
        final Integer medQuantity   = rs.getInt("medQuantity");
        final Integer stock         = rs.getInt("stock");
        final String timing         = rs.getString("timing");
        final Long timingId         = rs.getLong("timingId");
        final Double quantity         = rs.getDouble("quantity");
        final Double duration       = rs.getDouble("duration");
        final String period         = rs.getString("period");
        final Long periodId         = rs.getLong("periodId");
        final Double no             = rs.getDouble("no");
        final String timingUnit     = rs.getString("timingUnit");
        final Double mrpPrice       = rs.getDouble("mrpPrice");
        final Integer own           =rs.getInt("own");
        final String date = rs.getString("date");
        final Integer isFromSummary = rs.getInt("isFromSummary");
        final Integer isBilled = rs.getInt("is_billed");


        return PrevPrescriptionDetailsData.createNewInstance(displayNo,genId,genName,id,medName,formType,unit,unitId,medStrength,medQuantity,stock,timing,timingId,quantity,duration,period,periodId,no,timingUnit,mrpPrice,own,date,isFromSummary,isBilled);
    }
}
