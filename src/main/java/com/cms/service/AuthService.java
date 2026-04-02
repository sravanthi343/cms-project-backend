package com.cms.service;

import com.cms.config.JwtUtil;
<<<<<<< HEAD
import com.cms.dto.AuthResponse;
import com.cms.dto.LoginRequest;
import com.cms.dto.RegisterRequest;
=======
import com.cms.dto.*;
>>>>>>> 06559c1 (Initial commit for Render deployment)
import com.cms.model.User;
import com.cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
<<<<<<< HEAD

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // ── Register ─────────────────────────────────────────────────────────────
    public AuthResponse register(RegisterRequest req) {
        // Duplicate email check
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email '" + req.getEmail() + "' is already registered.");
        }
        // Duplicate userId check
        if (userRepository.existsByUserId(req.getUserId())) {
            throw new RuntimeException("User ID '" + req.getUserId() + "' is already taken.");
        }

        User user = new User();
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setUserId(req.getUserId());
        user.setRole(req.getRole());

        userRepository.save(user);

        return buildAuthResponse(user, "Registration successful");
    }

    // ── Login ─────────────────────────────────────────────────────────────────
    public AuthResponse login(LoginRequest req) {
        User user = userRepository.findByUserId(req.getUserId())
                .orElseThrow(() -> new RuntimeException("Invalid User ID or password."));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid User ID or password.");
        }

        return buildAuthResponse(user, "Login successful");
    }

    // ── Helper ────────────────────────────────────────────────────────────────
    private AuthResponse buildAuthResponse(User user, String message) {
        String token = jwtUtil.generateToken(user.getUserId(), user.getRole().name());

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setUserId(user.getUserId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setMessage(message);
        return response;
=======
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail()))
            throw new RuntimeException("Email already registered");
        if (userRepository.existsByUserId(req.getUserId()))
            throw new RuntimeException("User ID already taken");

        User user = User.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .userId(req.getUserId())
                .role(req.getRole())
                .build();
        userRepository.save(user);

        return buildResponse(user, "Registration successful");
    }

    public AuthResponse login(LoginRequest req) {
        User user = userRepository.findByUserId(req.getUserId())
                .orElseThrow(() -> new RuntimeException("Invalid User ID or password"));
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid User ID or password");
        return buildResponse(user, "Login successful");
    }

    private AuthResponse buildResponse(User user, String message) {
        String token = jwtUtil.generateToken(user.getUserId(), user.getRole().name());
        return AuthResponse.builder()
                .token(token).userId(user.getUserId())
                .fullName(user.getFullName()).email(user.getEmail())
                .role(user.getRole()).message(message).build();
>>>>>>> 06559c1 (Initial commit for Render deployment)
    }
}
