package com.store.discount.constants;

import java.text.DecimalFormat;

public enum ApplicationConstants {
    DATE_TIME_FORMAT("yyyy-MM-dd"),
    BILL_DISCOUNT_PATH("api/bill-discount"),
    DISCOUNT_MESSAGE_EMPLOYEE("YOU GOT 30% DISCOUNT FOR BEING AN EMPLOYEE!"),
    DISCOUNT_MESSAGE_AFFILIATE("YOU GOT 10% DISCOUNT FOR BEING AN AFFILIATE!"),
    DISCOUNT_MESSAGE_OLD_CUSTOMER("YOU GOT 5% DISCOUNT FOR BEING AN CUSTOMER FOR MORE THAN 2 YEARS!"),
    DISCOUNT_MESSAGE_REGULAR("YOU GOT $5 DISCOUNT FOR EACH $100 PURCHASED!");

    public static final DecimalFormat df = new DecimalFormat("#.##");

    private final Object constants;

    ApplicationConstants(Object constant){
        this.constants = constant;
    }

    public final Object getConstant(){
        return constants;
    }
}
