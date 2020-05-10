package com.store.discount.models;

public class ShoppingCart {
    private Bill bill;
    private Customer customer;

    public ShoppingCart(Bill bill, Customer customer) {
        this.bill = bill;
        this.customer = customer;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
