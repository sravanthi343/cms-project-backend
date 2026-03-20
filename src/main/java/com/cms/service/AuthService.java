package com.cms.service;

import com.cms.config.JwtUtil;
import com.cms.dto.AuthResponse;
import com.cms.dto.LoginRequest;
import com.cms.dto.RegisterRequest;
import com.cms.model.User;
import com.cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

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
    }
}
