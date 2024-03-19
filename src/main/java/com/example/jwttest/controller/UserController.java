package com.example.jwttest.controller;

import com.example.jwttest.entity.User;
import com.example.jwttest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5174")
public class UserController {
    private final UserService userService;

    @PostMapping("/api/v1/users")
    public ResponseEntity<?> create(@RequestBody User user) {
        return new ResponseEntity<>(userService.create(user), HttpStatus.OK);
    }


}