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
