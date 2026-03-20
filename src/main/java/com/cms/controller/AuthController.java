package com.cms.controller;

import com.cms.dto.ApiResponse;
import com.cms.dto.AuthResponse;
import com.cms.dto.LoginRequest;
import com.cms.dto.RegisterRequest;
import com.cms.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * POST /api/auth/register
     *
     * Body:
     * {
     *   "fullName": "John Doe",
     *   "email": "john@college.edu",
     *   "password": "secret123",
     *   "userId": "STU002",
     *   "role": "STUDENT"
     * }
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(
            @Valid @RequestBody RegisterRequest request) {

        AuthResponse authResponse = authService.register(request);
        return ResponseEntity.ok(
            ApiResponse.success("Registration successful", authResponse)
        );
    }

    /**
     * POST /api/auth/login
     *
     * Body:
     * {
     *   "userId": "STU001",
     *   "password": "student123"
     * }
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(
            @Valid @RequestBody LoginRequest request) {

        AuthResponse authResponse = authService.login(request);
        return ResponseEntity.ok(
            ApiResponse.success("Login successful", authResponse)
        );
    }
}
