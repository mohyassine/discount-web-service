package com.store.discount.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(TransactionId.class)
@Table(name = "transactions")
public class Transaction implements Serializable {

    @Id
    private long id;
    @Id
    private long customerId;
    @Id
    private long productId;

    public Transaction() {
    }

    public Transaction(long id, long customerId, long productId) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
