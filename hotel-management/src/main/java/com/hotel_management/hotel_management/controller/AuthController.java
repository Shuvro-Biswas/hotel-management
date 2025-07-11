package com.hotel_management.hotel_management.controller;

import com.hotel_management.hotel_management.dto.LoginRequest;
import com.hotel_management.hotel_management.dto.SignupRequest;
import com.hotel_management.hotel_management.entity.User;
import com.hotel_management.hotel_management.service.AuthService;
import com.hotel_management.hotel_management.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


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

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            System.out.println("🔐 Attempting authentication for: " + request.getUsername());

            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            System.out.println("✅ Authentication successful for: " + request.getUsername());

            String token = jwtUtil.generateToken(request.getUsername());
            System.out.println("🎫 JWT generated: " + token);

            return ResponseEntity.ok(token);

        } catch (Exception e) {
            e.printStackTrace(); // 👈 This will show the real issue if JWT fails
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }


}
