package com.cms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String fullName;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public enum Role { STUDENT, FACULTY }

    // ── No-args constructor ──────────────────────────────────────────────────
    public User() {}

    // ── All-args constructor ─────────────────────────────────────────────────
    public User(Long id, String fullName, String email,
                String password, String userId, Role role) {
        this.id       = id;
        this.fullName = fullName;
        this.email    = email;
        this.password = password;
        this.userId   = userId;
        this.role     = role;
    }

    // ── Getters ──────────────────────────────────────────────────────────────
    public Long   getId()       { return id; }
    public String getFullName() { return fullName; }
    public String getEmail()    { return email; }
    public String getPassword() { return password; }
    public String getUserId()   { return userId; }
    public Role   getRole()     { return role; }

    // ── Setters ──────────────────────────────────────────────────────────────
    public void setId(Long id)             { this.id = id; }
    public void setFullName(String fn)     { this.fullName = fn; }
    public void setEmail(String email)     { this.email = email; }
    public void setPassword(String pwd)    { this.password = pwd; }
    public void setUserId(String userId)   { this.userId = userId; }
    public void setRole(Role role)         { this.role = role; }
}
