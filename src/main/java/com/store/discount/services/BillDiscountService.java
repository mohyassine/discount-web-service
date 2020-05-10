package com.store.discount.services;


import com.store.discount.models.DiscountedBill;
import com.store.discount.models.ShoppingCart;

public interface BillDiscountService {

    DiscountedBill getDiscountedBill(double totalBillValue, double discountValue);
}
