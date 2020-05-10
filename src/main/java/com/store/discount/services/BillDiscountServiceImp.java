package com.store.discount.services;

import com.store.discount.models.DiscountedBill;
import com.store.discount.models.ShoppingCart;
import org.springframework.stereotype.Service;

@Service
public class BillDiscountServiceImp implements BillDiscountService {

    @Override
    public DiscountedBill getDiscountedBill(double totalBillValue, double discountValue) {
        return new DiscountedBill(totalBillValue, discountValue, totalBillValue-discountValue);
    }
}
