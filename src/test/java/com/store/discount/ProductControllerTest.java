package com.store.discount;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.discount.constants.AppConstants;
import com.store.discount.helpers.Utility;
import com.store.discount.models.ProductCategory;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
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
public class ProductControllerTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testInsertAndGetGroceryProducts() throws Exception {

        ProductCategory category = ProductCategory.GROCERY;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.PRODUCTS_URI)
                .content(mapper.writeValueAsString(Utility.createProductWithRandomPrice(category)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        double price = (double) jsonObject.get("price");

        mockMvc.perform(MockMvcRequestBuilders.get(AppConstants.PRODUCTS_URI))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[-1:].price").value(price))
                .andExpect(MockMvcResultMatchers.jsonPath("$[-1:].category").value(category.toString()))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testInsertAndGetOtherProducts() throws Exception {

        ProductCategory category = ProductCategory.OTHER;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(AppConstants.PRODUCTS_URI)
                .content(mapper.writeValueAsString(Utility.createProductWithRandomPrice(category)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
        double price = (double) jsonObject.get("price");

        mockMvc.perform(MockMvcRequestBuilders.get(AppConstants.PRODUCTS_URI))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[-1:].id").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[-1:].price").value(price))
                .andExpect(MockMvcResultMatchers.jsonPath("$[-1:].category").value(category.toString()))
                .andDo(print());
    }

}
