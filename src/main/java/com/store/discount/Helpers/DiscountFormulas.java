package com.store.discount.Helpers;

import com.store.discount.constants.DiscountConstants;
import com.store.discount.models.Bill;
import com.store.discount.models.Product;
import com.store.discount.models.ProductCategory;

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
