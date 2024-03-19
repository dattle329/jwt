package com.example.jwttest.service;

import com.example.jwttest.dto.UserDto;
import com.example.jwttest.entity.Role;
import com.example.jwttest.entity.User;
import com.example.jwttest.repository.RoleRepository;
import com.example.jwttest.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    @Override
    public ResponseEntity<?> create(User user) {
        var encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);
        var role = roleRepository.findByType(Role.Type.USER);
        user.setRoles(Set.of(role));
        UserDto userDtoMapper = mapper.map(savedUser, UserDto.class);
        return new ResponseEntity<>(userDtoMapper, HttpStatus.OK);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(username);
        }

        var authorities = new ArrayList<GrantedAuthority>();
        var roles = user.getRoles();
        for (Role role : roles) {
            var authority = new SimpleGrantedAuthority(role.getType().toString());
            authorities.add(authority);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
