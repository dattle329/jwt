package com.example.jwttest;

import com.example.jwttest.entity.Role;
import com.example.jwttest.entity.User;
import com.example.jwttest.repository.RoleRepository;
import com.example.jwttest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@AllArgsConstructor
@SpringBootApplication
public class JwtTestApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(JwtTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var user = new User();
        user.setUsername("dattle329");
        user.setPassword(passwordEncoder.encode("123456789@"));
        var adminRole = roleRepository.findByType(Role.Type.ADMIN);
        var userRole = roleRepository.findByType(Role.Type.USER);
        user.setRoles(Set.of(adminRole, userRole));
        userRepository.save(user);
    }
}
