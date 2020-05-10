package com.store.discount.constants;

import java.text.DecimalFormat;

public enum ApplicationConstants {
    DATE_TIME_FORMAT("yyyy-MM-dd"),
    BILL_DISCOUNT_PATH("api/bill-discount");

    public static final DecimalFormat df = new DecimalFormat("#.##");

    private final Object constants;

    ApplicationConstants(Object constant){
        this.constants = constant;
    }

    public final Object getConstant(){
        return constants;
    }
}
