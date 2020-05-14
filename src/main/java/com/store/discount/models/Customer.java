package com.store.discount.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customers")
public class Customer {

    private long id;
    private CustomerType type;
    private Date firstPurchaseDate;

    public Customer() {
    }

    public Customer(long id, CustomerType type, Date firstPurchaseDate) {
        this.id = id;
        this.type = type;
        this.firstPurchaseDate = firstPurchaseDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "type", nullable = false)
    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    @Column(name = "first_purchase", nullable = false)
    public Date getFirstPurchaseDate() {
        return firstPurchaseDate;
    }

    public void setFirstPurchaseDate(Date firstPurchaseDate) {
        this.firstPurchaseDate = firstPurchaseDate;
    }

}

