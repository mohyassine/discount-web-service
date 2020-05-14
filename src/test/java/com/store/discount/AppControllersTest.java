package com.store.discount;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.discount.constants.AppConstants;
import com.store.discount.helpers.Utility;
import com.store.discount.models.CustomerType;
import com.store.discount.models.ProductCategory;
import com.store.discount.models.Transaction;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppControllersTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;


    //Insert Employee Customer
    @Test
    @Order(1)
    public void testInsertEmployee() throws Exception {
        CustomerType customerType = CustomerType.EMPLOYEE;

        mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.CUSTOMERS_URI)
                .content(mapper.writeValueAsString(Utility.createCustomer(customerType)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value(customerType.toString()))
                .andDo(print());
    }

    //Insert affiliate Customer
    @Test
    @Order(2)
    public void testInsertAffiliate() throws Exception {
        CustomerType customerType = CustomerType.AFFILIATE;

        mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.CUSTOMERS_URI)
                .content(mapper.writeValueAsString(Utility.createCustomer(customerType)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value(customerType.toString()))
                .andDo(print());
    }

    //Insert a customer for over 2 years
    @Test
    @Order(3)
    public void testInsertLoyalCustomer() throws Exception {
        CustomerType customerType = CustomerType.CUSTOMER;

        mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.CUSTOMERS_URI)
                .content(mapper.writeValueAsString(Utility.createLoyalCustomer(customerType)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value(customerType.toString()))
                .andDo(print());
    }

    //Insert a regular customer
    @Test
    @Order(4)
    public void testInsertRegularCustomer() throws Exception {
        CustomerType customerType = CustomerType.CUSTOMER;

        mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.CUSTOMERS_URI)
                .content(mapper.writeValueAsString(Utility.createCustomer(customerType)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.type").value(customerType.toString()))
                .andDo(print());
    }

    // Product insertion tests
    @Test
    @Order(5)
    public void testInsertGroceryProducts() throws Exception {

        ProductCategory category = ProductCategory.GROCERY;

        mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.PRODUCTS_URI)
                .content(mapper.writeValueAsString(Utility.createProductWithRandomPrice(category)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value(ProductCategory.GROCERY.toString()))
                .andDo(print())
                .andReturn();
    }

    @Test
    @Order(6)
    public void testInsertOtherProducts() throws Exception {

        ProductCategory category = ProductCategory.OTHER;
        double price = 990;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.PRODUCTS_URI)
                .content(mapper.writeValueAsString(Utility.createProduct(category, price)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(price))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value(ProductCategory.OTHER.toString()))
                .andDo(print())
                .andReturn();
    }


    // Insert 4 transactions - one for each type of customers.
    @Test
    @Order(7)
    public void testInsertTransactions() throws Exception {
        long transactionId = 1;
        long productId = 2;

        // Transaction for customer 1 (Employee)
        long customerId = 1;
        Transaction transaction = new Transaction(transactionId, customerId, productId);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions/")
                .content(mapper.writeValueAsString(transaction))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").value(transaction.getCustomerId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(productId))
                .andDo(print());

        // Transaction for customer 2 (Affiliate)
        transaction.setCustomerId(2);
        mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.TRANSACTIONS_URI)
                .content(mapper.writeValueAsString(transaction))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").value(transaction.getCustomerId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(productId))
                .andDo(print());

        // Transaction for customer 3 (Loyal Customer)
        transaction.setCustomerId(3);
        mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.TRANSACTIONS_URI)
                .content(mapper.writeValueAsString(transaction))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").value(transaction.getCustomerId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(transaction.getProductId()))
                .andDo(print());

        // Transaction for customer 4 (Regular Customer)
        transaction.setCustomerId(4);
        mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.TRANSACTIONS_URI)
                .content(mapper.writeValueAsString(transaction))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").value(transaction.getCustomerId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(transaction.getProductId()))
                .andDo(print());
    }

    // Final test method to test the discount on 4 different bills - one for each customer available in the DB
    @Test
    @Order(8)
    public void testDiscounts() throws Exception {

        // Test employee discount
        long transactionId = 1;
        long customerId = 1;
        mockMvc.perform(MockMvcRequestBuilders.get(AppConstants.TRANSACTIONS_URI + transactionId + "/customer/" + customerId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalValue").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.discountValue").value(297))
                .andExpect(MockMvcResultMatchers.jsonPath("$.netPayable").value(693))
                .andDo(print());

        // Test affiliate discount
        customerId = 2;
        mockMvc.perform(MockMvcRequestBuilders.get(AppConstants.TRANSACTIONS_URI + transactionId + "/customer/" + customerId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalValue").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.discountValue").value(99))
                .andExpect(MockMvcResultMatchers.jsonPath("$.netPayable").value(891))
                .andDo(print());

        // Test affiliate discount
        customerId = 3;
        mockMvc.perform(MockMvcRequestBuilders.get(AppConstants.TRANSACTIONS_URI + transactionId + "/customer/" + customerId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalValue").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.discountValue").value(49.5))
                .andExpect(MockMvcResultMatchers.jsonPath("$.netPayable").value(940.5))
                .andDo(print());

        // Test affiliate discount
        customerId = 4;
        mockMvc.perform(MockMvcRequestBuilders.get(AppConstants.TRANSACTIONS_URI + transactionId + "/customer/" + customerId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalValue").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.discountValue").value(45))
                .andExpect(MockMvcResultMatchers.jsonPath("$.netPayable").value(945))
                .andDo(print());
    }


    // Test exceptions
    @Test
    @Order(9)
    public void testExceptions() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/all-customers/"))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andReturn();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers/"))
                .andExpect(status().isInternalServerError())
                .andDo(print());
    }
}
