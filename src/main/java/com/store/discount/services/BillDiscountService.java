package com.store.discount.services;


import com.store.discount.models.DiscountedBill;

public interface BillDiscountService {

    DiscountedBill getDiscountedBill(double totalBillValue, double discountValue);
}
