package com.example.jwttest.service;

import com.example.jwttest.dto.ProductDto;
import com.example.jwttest.entity.Product;
import com.example.jwttest.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Override
    public Page<ProductDto> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(product -> mapper.map(product, ProductDto.class));
    }

    @Override
    public ProductDto findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return mapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto create(Product product) {
        var savedProduct = productRepository.save(product);
        return mapper.map(savedProduct, ProductDto.class);
    }
}
