package com.store.discount.services;

import com.store.discount.models.DiscountedBill;
import org.springframework.stereotype.Service;

@Service
public class BillDiscountServiceImp implements BillDiscountService {

    @Override
    public DiscountedBill getDiscountedBill(double totalBillValue, double discountValue) {
        DiscountedBill discountedBill = new DiscountedBill();
        discountedBill.setTotalValue(totalBillValue);
        discountedBill.setDiscountValue(discountValue);
        discountedBill.setNetPayable(totalBillValue-discountValue);
        return discountedBill;
    }
}
