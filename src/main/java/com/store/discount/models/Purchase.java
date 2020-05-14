package com.store.discount.models;

import java.util.Date;


public interface Purchase {

    Long getTransactionId();

    // Customer
    Long getCustomerId();

    CustomerType getCustomerType();

    Date getFirstPurchase();

    // Product
    Long getProductId();

    ProductCategory getCategory();

    Long getPrice();

}
