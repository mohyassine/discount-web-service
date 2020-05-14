package com.store.discount.controllers;


import com.store.discount.helpers.DiscountFormulas;
import com.store.discount.helpers.Utility;
import com.store.discount.models.DiscountedBill;
import com.store.discount.models.Purchase;
import com.store.discount.models.Transaction;
import com.store.discount.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @PostMapping("/")
    public Transaction createTransaction(@Valid @RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @GetMapping("/{transactionId}/customer/{customerId}")
    public DiscountedBill getBillDiscount(@PathVariable(value = "transactionId") long transactionId,
                                          @PathVariable(value = "customerId") long customerId) {

        List<Purchase> purchases = transactionRepository.queryBy(transactionId, customerId);
        double discountValue = 0;
        double totalBillValue = Utility.getBillTotalValue(purchases);
        double totalNonGroceryValue = DiscountFormulas.getTotalNonGroceryBillValue(purchases);

        if (purchases.size() > 0) {

            switch (purchases.get(0).getCustomerType()) {
                case EMPLOYEE:
                    discountValue = DiscountFormulas.getEmployeeDiscount(totalNonGroceryValue);
                    break;
                case AFFILIATE:
                    discountValue = DiscountFormulas.getAffiliateDiscount(totalNonGroceryValue);
                    break;
                case CUSTOMER:
                    if (Utility.isOldCustomer(purchases.get(0).getFirstPurchase())) {
                        discountValue = DiscountFormulas.getCustomerDiscount(totalNonGroceryValue);
                    } else
                        discountValue = DiscountFormulas.getRegularDiscount(totalNonGroceryValue);
                    break;
            }
        }
        return Utility.getDiscountedBill(totalBillValue, discountValue);
    }
}
