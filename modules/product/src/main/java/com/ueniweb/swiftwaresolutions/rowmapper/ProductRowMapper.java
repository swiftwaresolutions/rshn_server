package com.ueniweb.swiftwaresolutions.rowmapper;

import com.ueniweb.swiftwaresolutions.data.MedicineNameData;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
@Data
public class ProductRowMapper implements RowMapper<MedicineNameData>  {

    private final String schema;

    public ProductRowMapper(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM ph_prods a ");
        builder.append(" LEFT JOIN ph_prods_forms b ON a.`form_id`=b.`id` ");
        builder.append(" LEFT JOIN ph_generic c ON a.`generic_id`=c.`id` ");
        builder.append(" LEFT JOIN ph_prods_unit d ON a.`units_id`=d.`id` ");

        this.schema = builder.toString();
    }

    public String schema() {
        return this.schema;
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" a.id,a.name AS medName,b.form_type AS formType,c.`id` AS genId,c.`name`AS genName,d.`name` AS unit,a.strength as medStrength,a.quantity as medQuantity,getStoreStock(a.id,1)  AS stock ");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public MedicineNameData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Integer id         = rs.getInt("id");
        final String medName     = rs.getString("medName");
        final Integer formType   = rs.getInt("formType");
        final Integer genId      = rs.getInt("genId");
        final String genName     = rs.getString("genName");
        final String unit        = rs.getString("unit");
        final Integer medStrength   = rs.getInt("medStrength");
        final Integer medQuantity   = rs.getInt("medQuantity");
        final Integer stock    = rs.getInt("stock");

        return MedicineNameData.createNewInstance(id,medName,formType,genId,genName,unit,medStrength,medQuantity,stock);
    }
}
