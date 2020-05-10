package com.store.discount.models;

public class DiscountedBill {
    private double totalValue;
    private double discountValue;
    private double netPayable;

    public DiscountedBill(double totalValue, double discountValue, double netPayable) {
        this.totalValue = totalValue;
        this.discountValue = discountValue;
        this.netPayable = netPayable;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public double getNetPayable() {
        return netPayable;
    }

    public void setNetPayable(double netPayable) {
        this.netPayable = netPayable;
    }
}
