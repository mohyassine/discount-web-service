package com.store.discount.controllers;

import com.store.discount.models.Product;
import com.store.discount.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

}
