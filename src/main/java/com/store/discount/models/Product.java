package com.store.discount.models;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    private long id;
    private ProductCategory category;
    private double price;

    public Product() {
    }

    public Product(ProductCategory category, double price) {
        this.category = category;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "category", nullable = false)
    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    @Column(name = "price", nullable = false)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
