package com.example.movieplatform.inquiry.entity;

import com.example.movieplatform.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "inquiry_answers")
@Getter
public class InquiryAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public InquiryAnswer(){}

    public InquiryAnswer(String content, Inquiry inquiry, User user) {
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.inquiry = inquiry;
        this.user = user;
    }
}
