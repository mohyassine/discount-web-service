package com.store.discount.models;

import java.io.Serializable;

public class TransactionId implements Serializable {
    private long id;
    private long customerId;
    private long productId;

    public TransactionId() {
    }

    public TransactionId(long id, long customerId, long productId) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}