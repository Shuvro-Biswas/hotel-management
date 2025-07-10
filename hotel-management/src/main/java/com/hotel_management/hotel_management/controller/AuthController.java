package com.hotel_management.hotel_management.controller;

import com.hotel_management.hotel_management.dto.LoginRequest;
import com.hotel_management.hotel_management.dto.SignupRequest;
import com.hotel_management.hotel_management.entity.User;
import com.hotel_management.hotel_management.service.AuthService;
import com.hotel_management.hotel_management.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private AuthService authService;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignupRequest request) {
        User user = authService.register(request);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = authService.authenticate(request);
        if (user == null) return ResponseEntity.status(401).body("Invalid credentials");
        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(token);
    }
}
