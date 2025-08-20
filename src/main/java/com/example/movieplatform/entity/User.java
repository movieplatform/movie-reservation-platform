package com.example.movieplatform.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private LocalDate birthDate;
    private LocalDateTime createdAt;
    private LocalDateTime currentLoginAt;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private AuthType authType;

    public enum Status{
        ACTIVE,
        DELETED
    }

    public enum AuthType{
        LOCAL,
        PAYCO
    }
}
