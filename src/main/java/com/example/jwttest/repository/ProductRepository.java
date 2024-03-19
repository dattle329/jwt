package com.example.jwttest.repository;


import com.example.jwttest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsById(Long id);
}
