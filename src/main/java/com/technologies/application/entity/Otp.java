package com.technologies.application.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "otp_verification")
@Data
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String mobileNumber;
    @Column(nullable = false)
    private String otp;
    private LocalDateTime expiryTime;
    private int attemptCount;
    private int rateLimit;
    private boolean verified;
}
