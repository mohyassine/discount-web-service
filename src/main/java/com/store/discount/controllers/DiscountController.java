package com.store.discount.controllers;

import com.store.discount.helpers.DiscountFormulas;
import com.store.discount.helpers.Utils;
import com.store.discount.models.Bill;
import com.store.discount.models.DiscountedBill;
import com.store.discount.models.ShoppingCart;
import com.store.discount.services.BillDiscountService;
import com.store.discount.services.BillDiscountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DiscountController {

    @Autowired
    private BillDiscountService billDiscountService = new BillDiscountServiceImp();

    @GetMapping("/")
    public String index() {
        return "Hello from Store Discount App!";
    }

    @PostMapping("api/bill-discount")
    public DiscountedBill createDiscount(@Valid @RequestBody ShoppingCart shoppingCart) {

        Bill bill = shoppingCart.getBill();
        double totalBillValue = Utils.getBillTotalValue(shoppingCart.getBill().getProducts());
        double totalNonGroceryValue = DiscountFormulas.getTotalNonGroceryBillValue(bill);
        double discountValue = 0;

        switch (shoppingCart.getCustomer().getType()) {
            case EMPLOYEE:
                discountValue = DiscountFormulas.getEmployeeDiscount(totalNonGroceryValue);
                break;
            case AFFILIATE:
                discountValue = DiscountFormulas.getAffiliateDiscount(totalNonGroceryValue);
                break;
            case CUSTOMER:
                if (Utils.isOldCustomer(shoppingCart.getCustomer())) {
                    discountValue = DiscountFormulas.getCustomerDiscount(totalNonGroceryValue);
                } else
                    discountValue = DiscountFormulas.getRegularDiscount(totalNonGroceryValue);
                break;
        }

        return billDiscountService.getDiscountedBill(totalBillValue, discountValue);
    }

}
