package com.example.movieplatform.entity;

import com.example.movieplatform.user.entity.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "point_history")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pointAmount;
    private String description;
    private LocalDateTime transactionDate;
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public enum Type
    {
        EARN,   // 적립
        USE     // 사용
    }
}
