package com.ueniweb.swiftwaresolutions.rowmapper;

import com.ueniweb.swiftwaresolutions.data.StoreWiseAvailableStockData;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class StoreWiseAvailableStockRowMapper implements RowMapper<StoreWiseAvailableStockData> {

    private final String schema;

    public StoreWiseAvailableStockRowMapper(){
        final StringBuilder builder = new StringBuilder(200);
        builder.append(" FROM (SELECT a1.id AS storeId,a1.name AS storeName,SUM(a4.stock) AS avialableStock,AVG(a3.mrp) AS mrpPrice " +
                       " FROM ph_config_stores a1,ph_prods a2,ph_batch a3,ph_store_batch_totals a4 ");
        this.schema = builder.toString();
    }

    public String schema() {
        return this.schema;
    }

    public String tableSchema() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("a5.storeId,a5.storeName,a5.avialableStock,a5.mrpPrice");
        builder.append(this.schema);
        return builder.toString();
    }

    @Override
    public StoreWiseAvailableStockData mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Integer storeId       = rs.getInt("storeId");
        final String storeName      = rs.getString("storeName");
        final Double avialableStock = rs.getDouble("avialableStock");
        final Double mrpPrice      = rs.getDouble("mrpPrice");
        return StoreWiseAvailableStockData.createNewInstance(storeId,storeName,avialableStock,mrpPrice);
    }
}
