package com.ueniweb.swiftwaresolutions.rowmapper;
import com.ueniweb.swiftwaresolutions.data.PrevPrescDetailsData;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class PrevPrescriptionDetailsRowMapper implements RowMapper<PrevPrescDetailsData> {

    private final String schema;

    public PrevPrescriptionDetailsRowMapper(){
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

    /*public String tableSchema(Integer storeId) {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("b.display as displayNo,d.id AS genId,d.`name` AS genName,e.`id`,e.`name` AS medName,m.`form_type` AS formType,h.`name` AS unit,h.`id` AS unitId,\n" +
                " e.`strength` AS medStrength,e.`quantity` AS medQuantity,getStoreStock(e.id,"+storeId+")  AS stock,i.`name`AS timing,i.id AS timingId,a.`quantity`,a.`duration`,j.`name`AS period,\n" +
                " j.`id`AS periodId,a.`qno` as no,ifnull(a.timingUnits,'0-0-0') as timingUnit,getBatchMrpRate(e.`name`) as mrpPrice,a.is_own as own ,a.date AS DATE,b.isFromSummary,b.is_billed ");
        builder.append(this.schema);
        return builder.toString();
    }*/

    public String tableSchema(
            Integer storeId,
            Long patId) {

        StringBuilder builder =
                new StringBuilder();

        /*
         * NORMAL MEDICINES
         */
        builder.append(
                " SELECT " +
                        " b.display as displayNo," +
                        " d.id AS genId," +
                        " d.name AS genName," +
                        " e.id," +
                        " e.name AS medName," +
                        " m.form_type AS formType," +
                        " h.name AS unit," +
                        " h.id AS unitId," +
                        " e.strength AS medStrength," +
                        " e.quantity AS medQuantity," +
                        " getStoreStock(e.id," + storeId + ") AS stock," +
                        " i.name AS timing," +
                        " i.id AS timingId," +
                        " a.quantity," +
                        " a.duration," +
                        " j.name AS period," +
                        " j.id AS periodId," +
                        " a.qno AS no," +
                        " ifnull(a.timingUnits,'0-0-0') AS timingUnit," +
                        " getBatchMrpRate(e.name) AS mrpPrice," +
                        " a.is_own AS own," +
                        " a.date," +
                        " b.isFromSummary," +
                        " b.is_billed," +
                        " 0 AS isManual " +
                        this.schema +
                        " WHERE a.generic_id=d.id " +
                        " AND a.prods_id=e.id " +
                        " AND a.prescription_id=b.id " +
                        " AND b.pat_id=" + patId +
                        " AND a.is_cancelled=0 "
        );

        /*
         * MANUAL MEDICINES
         */
        builder.append(
                " UNION ALL " +
                        " SELECT " +
                        " b.display AS displayNo," +
                        " 0 AS genId," +
                        " '' AS genName," +
                        " a.id," +
                        " a.med_name AS medName," +
                        " 0 AS formType," +
                        " h.name AS unit," +
                        " h.id AS unitId," +
                        " 0 AS medStrength," +
                        " 0 AS medQuantity," +
                        " 0 AS stock," +
                        " i.name AS timing," +
                        " i.id AS timingId," +
                        " a.quantity," +
                        " 0 AS duration," +
                        " '' AS period," +
                        " 0 AS periodId," +
                        " a.quantity AS no," +
                        " '0-0-0' AS timingUnit," +
                        " 0 AS mrpPrice," +
                        " a.is_own AS own," +
                        " '' AS date," +
                        " b.isFromSummary," +
                        " b.is_billed," +
                        " 1 AS isManual " +
                        " FROM ph_prescription_details_manual_med a " +
                        " INNER JOIN ph_prescription b " +
                        "     ON a.prescription_id=b.id " +
                        " LEFT JOIN ph_prods_unit h " +
                        "     ON a.unit=h.id " +
                        " LEFT JOIN nur_master_timing i " +
                        "     ON a.timing=i.id " +
                        " WHERE b.pat_id=" + patId +
                        " AND a.is_cancelled=0 "
        );

        builder.append(
                " ORDER BY displayNo DESC "
        );

        return builder.toString();
    }

    @Override
    public PrevPrescDetailsData mapRow(ResultSet rs, int rowNum) throws SQLException {

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
        final Integer isManual = rs.getInt("isManual");


        return PrevPrescDetailsData.createNewInstance(displayNo,genId,genName,id,medName,formType,unit,unitId,medStrength,medQuantity,stock,timing,timingId,quantity,duration,period,periodId,no,timingUnit,mrpPrice,own,date,isFromSummary,isBilled,isManual);
    }
}
