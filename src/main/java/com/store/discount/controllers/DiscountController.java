package com.store.discount.controllers;

import com.store.discount.DiscountApplication;
import com.store.discount.models.ShoppingCart;
import com.store.discount.services.BillDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("api/bill-discount")
public class DiscountController {

    @Autowired
    private BillDiscountService billDiscountService;


}
