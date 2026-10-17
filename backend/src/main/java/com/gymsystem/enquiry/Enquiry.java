package com.gymsystem.enquiry;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "enquiries")
public class Enquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @Column(name = "preferred_plan", length = 30)
    private String preferredPlan;

    @Column(name = "preferred_date")
    private LocalDate preferredDate;

    @Column(name = "status", nullable = false, length = 15)
    private String status = "NEW";

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected Enquiry() {
    }

    public Enquiry(String name, String phone, String preferredPlan, LocalDate preferredDate) {
        this.name = name;
        this.phone = phone;
        this.preferredPlan = preferredPlan;
        this.preferredDate = preferredDate;
    }

    @PrePersist
    void setCreatedAt() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now(Clock.systemUTC());
        }
    }

    public Long getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getPhone() {
        return phone;
    }

    String getPreferredPlan() {
        return preferredPlan;
    }

    LocalDate getPreferredDate() {
        return preferredDate;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}