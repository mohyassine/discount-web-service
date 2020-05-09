package com.store.discount.services;


import com.store.discount.models.ShoppingCart;

public interface BillDiscountService {

    double getDiscount(ShoppingCart shoppingCart);
}
