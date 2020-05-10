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

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
