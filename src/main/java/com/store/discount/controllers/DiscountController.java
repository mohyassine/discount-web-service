package com.store.discount.controllers;

import com.store.discount.Helpers.DiscountFormulas;
import com.store.discount.Helpers.Utils;
import com.store.discount.models.Bill;
import com.store.discount.models.DiscountedBill;
import com.store.discount.models.ShoppingCart;
import com.store.discount.services.BillDiscountService;
import com.store.discount.services.BillDiscountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("api/bill-discount")
public class DiscountController {

    @Autowired
    private BillDiscountService billDiscountService = new BillDiscountServiceImp();

    @PostMapping
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
            default:
        }

        return billDiscountService.getDiscountedBill(totalBillValue, discountValue);
    }

}
