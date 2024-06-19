package com.ueniweb.swiftwaresolutions.data;

import lombok.Data;

@Data
public class MedicineNameData {

    private Integer id;

    private String medName;

    private Integer formType;

    private Integer genId;

    private String genName;

    private String unit;

    private Integer medStrength;

    private Integer medQuantity;

    private Integer stock;

    public MedicineNameData(final Integer id, final String medName,final Integer formType,final Integer genId,final String genName,final String unit,final Integer medStrength,final Integer medQuantity,Integer stock) {
        this.id         = id;
        this.medName    = medName;
        this.formType   = formType;
        this.genId      = genId;
        this.genName    = genName;
        this.unit       = unit;
        this.medStrength   = medStrength;
        this.medQuantity   = medQuantity;
        this.stock          = stock;
    }

    public static MedicineNameData createNewInstance(final Integer id, final String medName,final Integer formType,final Integer genId,final String genName,final String unit,final Integer medStrength,final Integer medQuantity,Integer stock) {
        return new MedicineNameData(id,medName,formType,genId,genName,unit,medStrength,medQuantity,stock);
    }
}
