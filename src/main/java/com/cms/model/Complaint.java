package com.cms.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String description;

    @Column(nullable = false)
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String raisedBy;       // userId

    @Column(nullable = false)
    private String raisedByName;   // full name

    public enum Status { OPEN, IN_PROGRESS, RESOLVED, CLOSED }

    // ── Lifecycle ────────────────────────────────────────────────────────────
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) this.status = Status.OPEN;
    }

    // ── No-args constructor ──────────────────────────────────────────────────
    public Complaint() {}

    // ── All-args constructor ─────────────────────────────────────────────────
    public Complaint(Long id, String title, String description, String category,
                     Status status, LocalDateTime createdAt,
                     String raisedBy, String raisedByName) {
        this.id           = id;
        this.title        = title;
        this.description  = description;
        this.category     = category;
        this.status       = status;
        this.createdAt    = createdAt;
        this.raisedBy     = raisedBy;
        this.raisedByName = raisedByName;
    }

    // ── Getters ──────────────────────────────────────────────────────────────
    public Long          getId()           { return id; }
    public String        getTitle()        { return title; }
    public String        getDescription()  { return description; }
    public String        getCategory()     { return category; }
    public Status        getStatus()       { return status; }
    public LocalDateTime getCreatedAt()    { return createdAt; }
    public String        getRaisedBy()     { return raisedBy; }
    public String        getRaisedByName() { return raisedByName; }

    // ── Setters ──────────────────────────────────────────────────────────────
    public void setId(Long id)                       { this.id = id; }
    public void setTitle(String title)               { this.title = title; }
    public void setDescription(String description)   { this.description = description; }
    public void setCategory(String category)         { this.category = category; }
    public void setStatus(Status status)             { this.status = status; }
    public void setCreatedAt(LocalDateTime createdAt){ this.createdAt = createdAt; }
    public void setRaisedBy(String raisedBy)         { this.raisedBy = raisedBy; }
    public void setRaisedByName(String name)         { this.raisedByName = name; }
}
