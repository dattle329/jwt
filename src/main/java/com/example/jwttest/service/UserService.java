package com.example.jwttest.service;

import com.example.jwttest.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> create(User user);
    User findUserByUsername(String username);
}
