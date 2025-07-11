package com.hotel_management.hotel_management.service;

import com.hotel_management.hotel_management.dto.LoginRequest;
import com.hotel_management.hotel_management.dto.SignupRequest;
import com.hotel_management.hotel_management.entity.User;
import com.hotel_management.hotel_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    public User register(SignupRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        return userRepository.save(user);
    }

//    public User authenticate(LoginRequest request) {
//        return userRepository.findByUsername(request.getUsername())
//                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
//                .orElse(null);
//    }
    public User authenticate(LoginRequest request) {
        return userRepository.findByUsername(request.getUsername())
                .filter(user -> {
                    System.out.println("Login attempt for username: " + request.getUsername());
                    System.out.println("Raw password input: " + request.getPassword());
                    System.out.println("Hashed password in DB: " + user.getPassword());
                    boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());
                    System.out.println("Password matches? " + matches);
                    return matches;
                })
                .orElse(null);
    }

}
