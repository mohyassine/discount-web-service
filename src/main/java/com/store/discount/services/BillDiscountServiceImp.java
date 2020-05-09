package com.store.discount.services;

import com.store.discount.models.ShoppingCart;
import org.springframework.stereotype.Service;

@Service
public class BillDiscountServiceImp implements BillDiscountService {

    @Override
    public double getDiscount(ShoppingCart shoppingCart) {
        return 0;
    }
}
