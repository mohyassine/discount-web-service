package com.store.discount.Helpers;

import com.store.discount.models.Bill;
import com.store.discount.models.Product;
import com.store.discount.models.ProductCategory;
import com.store.discount.models.ShoppingCart;

public class DiscountFormulas {


    public static double getTotalNonGroceryBillValue(Bill bill) {
        double totalBillValue = 0;

        for (Product product : bill.getProducts()) {
            if (product.getCategory() == ProductCategory.OTHER) {
                totalBillValue += product.getPrice();
            }
        }

        return totalBillValue;
    }

    public static double getEmployeeDiscount(double billValue) {
        return billValue * 0.3;
    }

    public static double getAffiliateDiscount(double billValue) {
        return billValue * 0.1;
    }

    public static double getCustomerDiscount(double billValue) {
        return billValue * 0.05;
    }

    public static double getRegularDiscount(double billValue) {
        return (billValue - billValue%100)*0.05;
    }

}
