package com.store.discount.helpers;

import com.store.discount.models.Customer;
import com.store.discount.models.Product;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utils {

    private Utils() {}

    public static double getBillTotalValue(List<Product> productList) {
        double totalValue = 0;
        for (Product product : productList ) {
            totalValue += product.getPrice();
        }
        return totalValue;
    }

    /**
     * Old customer is she/he has been a consumer for 2 or more years.
     * @return true of customer's first purchase was two years old
     */
    public static boolean isOldCustomer(Customer customer) {
        boolean isOldCustomer;
        Date twoYearsAgo = createDate(-24); // 2 years back
        isOldCustomer = customer.getFirstPurchaseDate().before(twoYearsAgo);

        return isOldCustomer;
    }

    /**
     *  Create a date X months from today
     * @param monthsFromToday number of month to change from today
     * @return Date object
     */
    public static Date createDate(int monthsFromToday) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, monthsFromToday);
        return cal.getTime();
    }
}
