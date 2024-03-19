package com.example.jwttest.service;

import com.example.jwttest.dto.ProductDto;
import com.example.jwttest.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductDto> findAll(Pageable pageable);

    ProductDto findById(Long id);
    ProductDto create(Product product);
}
