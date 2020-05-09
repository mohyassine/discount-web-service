package com.store.discount.constants;

import java.text.DecimalFormat;

public enum ApplicationConstants {

    DATE_TIME_FORMAT("cccc dd-MMMM-uuuu hh:mm a");

    public static final DecimalFormat df = new DecimalFormat("#.##");

    private final Object appCons;


    ApplicationConstants(Object appCons){
        this.appCons = appCons;
    }

    public final Object getApplicationConstant(){
        return appCons;
    }
}
