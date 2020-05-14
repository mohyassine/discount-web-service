package com.store.discount.helpers;

import com.store.discount.constants.DiscountConstants;
import com.store.discount.models.ProductCategory;
import com.store.discount.models.Purchase;

import java.util.List;

public class DiscountFormulas {

    private DiscountFormulas() {
    }

    public static double getTotalNonGroceryBillValue(List<Purchase> purchases) {
        double totalBillValue = 0;

        for (Purchase purchase : purchases) {
            if (purchase.getCategory() == ProductCategory.OTHER) {
                totalBillValue += purchase.getPrice();
            }
        }

        return totalBillValue;
    }

    public static double getEmployeeDiscount(double billValue) {
        return billValue * DiscountConstants.EMPLOYEE.getDiscount();
    }

    public static double getAffiliateDiscount(double billValue) {
        return billValue * DiscountConstants.AFFILIATE.getDiscount();
    }

    public static double getCustomerDiscount(double billValue) {
        return billValue * DiscountConstants.LOYAL_CUSTOMER.getDiscount();
    }

    public static double getRegularDiscount(double billValue) {
        return (billValue - billValue % 100) * DiscountConstants.OTHER_CUSTOMERS.getDiscount();
    }

}
