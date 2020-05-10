package com.store.discount.models;

import java.util.List;

public class Bill {
    private List<Product> products;

//    public Bill(List<Product> products) {
//        this.products = products;
//    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
