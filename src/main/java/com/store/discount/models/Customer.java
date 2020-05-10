package com.store.discount.models;

import java.util.Date;

public class Customer {

    private CustomerType type;
    private Date firstPurchaseDate;

    public Customer(CustomerType type, Date firstPurchaseDate) {
        this.type = type;
        this.firstPurchaseDate = firstPurchaseDate;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public Date getFirstPurchaseDate() {
        return firstPurchaseDate;
    }

    public void setFirstPurchaseDate(Date firstPurchaseDate) {
        this.firstPurchaseDate = firstPurchaseDate;
    }

}

