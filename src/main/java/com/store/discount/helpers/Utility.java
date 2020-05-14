package com.store.discount.helpers;

import com.store.discount.models.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utility {

    public static double getBillTotalValue(List<Purchase> purchases) {
        double totalValue = 0;
        for (Purchase purchase : purchases) {
            totalValue += purchase.getPrice();
        }
        return totalValue;
    }

    /**
     * Old customer is she/he has been a consumer for 2 or more years.
     *
     * @return true of customer's first purchase was two years old
     */
    public static boolean isOldCustomer(Date firstPurchase) {
        boolean isOldCustomer;
        Date twoYearsAgo = createDate(-24); // 2 years back
        isOldCustomer = firstPurchase.before(twoYearsAgo);

        return isOldCustomer;
    }

    /**
     * Create a date X months from today
     *
     * @param monthsFromToday number of month to change from today
     * @return Date object
     */
    public static Date createDate(int monthsFromToday) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, monthsFromToday);
        return cal.getTime();
    }

    /**
     * Create a sample Product entity
     *
     * @param category
     * @return
     */
    public static Product createProduct(ProductCategory category, double price) {
        Product product = new Product();
        product.setCategory(category);
        product.setPrice(price);
        return product;
    }

    /**
     * Create a sample Product entity with random price
     *
     * @param category
     * @return
     */
    public static Product createProductWithRandomPrice(ProductCategory category) {
        Product product = new Product();
        product.setCategory(category);
        product.setPrice((int) (Math.random() * 1000));
        return product;
    }

    /**
     * Create a sample Customer entity
     *
     * @param customerType
     * @return
     */
    public static Customer createLoyalCustomer(CustomerType customerType) {
        Customer customer = new Customer();
        customer.setType(customerType);
        customer.setFirstPurchaseDate(Utility.createDate(-30));
        return customer;
    }

    public static Customer createCustomer(CustomerType customerType) {
        Customer customer = new Customer();
        customer.setType(customerType);
        customer.setFirstPurchaseDate(Utility.createDate(-2));
        return customer;
    }

    //util
    public static DiscountedBill getDiscountedBill(double totalBillValue, double discountValue) {
        DiscountedBill discountedBill = new DiscountedBill();
        discountedBill.setTotalValue(totalBillValue);
        discountedBill.setDiscountValue(discountValue);
        discountedBill.setNetPayable(totalBillValue - discountValue);
        return discountedBill;
    }
}
