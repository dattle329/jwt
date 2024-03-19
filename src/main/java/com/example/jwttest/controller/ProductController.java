package com.example.jwttest.controller;

import com.example.jwttest.dto.ProductDto;
import com.example.jwttest.entity.Product;
import com.example.jwttest.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5174")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/api/v1/products")
    public Page<ProductDto> findAll(Pageable pageable){
        return productService.findAll(pageable);
    }

    @GetMapping("/api/v1/product/{id}")
    public ProductDto findById(@PathVariable  Long id){
        return productService.findById(id);
    }


    @PostMapping("/api/v1/product")
    public ProductDto create(@RequestBody  Product product){
        return productService.create(product);
    }

}
