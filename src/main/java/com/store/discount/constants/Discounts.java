package com.store.discount.constants;

public enum Discounts {
    EMPLOYEE(0.3),
    AFFILIATE(0.1),
    OLD_CUSTOMER(0.05),
    OTHER_CUSTOMERS(0.05);

    private final double discount;

    Discounts(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}
