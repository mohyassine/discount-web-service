package com.store.discount.constants;

public enum DiscountConstants {
    EMPLOYEE(0.3),
    AFFILIATE(0.1),
    LOYAL_CUSTOMER(0.05),
    OTHER_CUSTOMERS(0.05);

    private final double discount;

    DiscountConstants(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}
