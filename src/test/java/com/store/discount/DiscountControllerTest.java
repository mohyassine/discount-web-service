package com.store.discount;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.discount.Helpers.Utils;
import com.store.discount.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DiscountControllerTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getHello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello from Store Discount App!")))
                .andDo(print())
                .andReturn().getResponse().getContentAsString();;
    }

    @Test
    public void getEmptyShoppingCartDiscount() throws Exception {

        ShoppingCart shoppingCart = fillShoppingCart();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/bill-discount")
                .content(mapper.writeValueAsString(shoppingCart))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.discountValue").value(45))
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
    }

    private ShoppingCart fillShoppingCart() {

        Bill bill = new Bill();
        List<Product> products = new ArrayList<>();
        products.add(new Product(ProductCategory.OTHER, 90));
        products.add(new Product(ProductCategory.OTHER, 500));
        products.add(new Product(ProductCategory.OTHER, 400));
        products.add(new Product(ProductCategory.GROCERY, 70));
        bill.setProducts(products);

        Date firstPurchaseDate = Utils.createDate(-20);
        Customer customer = new Customer(CustomerType.CUSTOMER, firstPurchaseDate);
        return new ShoppingCart(bill, customer);
    }
}

