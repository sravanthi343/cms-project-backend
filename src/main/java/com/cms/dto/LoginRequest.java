package com.cms.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Password is required")
    private String password;

    // ── No-args constructor ───────────────────────────────────────────────
    public LoginRequest() {}

    // ── All-args constructor ─────────────────────────────────────────────
    public LoginRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    // ── Getters ─────────────────────────────────────────────────────────
    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    // ── Setters ─────────────────────────────────────────────────────────
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
