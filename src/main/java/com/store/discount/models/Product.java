package com.store.discount.models;

public class Product {
    private ProductCategory category;
    private double price;

    public Product(ProductCategory category, double price) {
        this.category = category;
        this.price = price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

}
