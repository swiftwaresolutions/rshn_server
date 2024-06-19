package com.ueniweb.swiftwaresolutions.data;

import lombok.Data;

@Data
public class StoreWiseAvailableStockData {

    private Integer storeId;

    private String storeName;

    private Double avialableStock;

    private Double mrpPrice;

    public StoreWiseAvailableStockData(final Integer storeId, final String storeName, final Double avialableStock, final Double mrpPrice) {
        this.storeId        = storeId;
        this.storeName      = storeName;
        this.avialableStock = avialableStock;
        this.mrpPrice      = mrpPrice;
    }

    public static StoreWiseAvailableStockData createNewInstance(final Integer storeId, final String storeName, final Double avialableStock, final Double mrpPrice) {
        return new StoreWiseAvailableStockData(storeId,storeName,avialableStock,mrpPrice);
    }

}
