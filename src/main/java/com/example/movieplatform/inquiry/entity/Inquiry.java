package com.example.movieplatform.inquiry.entity;

import com.example.movieplatform.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "inquiries")
@Getter
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime createdAt;
    private InquiryStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public enum InquiryStatus {
        BEFORE_ANSWER,
        ANSWERED
    }
}
