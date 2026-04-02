package com.cms.dto;

import com.cms.model.User;
import jakarta.validation.constraints.*;

public class RegisterRequest {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Valid email required")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotNull(message = "Role is required (STUDENT or FACULTY)")
    private User.Role role;

    // ── No-args constructor ───────────────────────────────────────────────
    public RegisterRequest() {}

    // ── All-args constructor ─────────────────────────────────────────────
    public RegisterRequest(String fullName, String email, String password,
                           String userId, User.Role role) {
        this.fullName = fullName;
        this.email    = email;
        this.password = password;
        this.userId   = userId;
        this.role     = role;
    }

    // ── Getters ─────────────────────────────────────────────────────────
    public String getFullName() { return fullName; }
    public String getEmail()    { return email; }
    public String getPassword() { return password; }
    public String getUserId()   { return userId; }
    public User.Role getRole()  { return role; }

    // ── Setters ─────────────────────────────────────────────────────────
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email)       { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setUserId(String userId)     { this.userId = userId; }
    public void setRole(User.Role role)      { this.role = role; }
}
