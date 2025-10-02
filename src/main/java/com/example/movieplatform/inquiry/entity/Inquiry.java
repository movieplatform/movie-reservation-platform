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
    private InquiryType inquiryType;
    private InquiryStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Getter
    public enum InquiryType {
        MOVIE("영화"),
        REVIEW("리뷰"),
        RESERVATION_PAYMENT("예약/결제"),
        POINT("포인트"),
        USER("사용자"),
        THEATER_SCREEN("극장/상영관");

        private final String label;

        InquiryType(String label) {
            this.label = label;
        }
    }

    @Getter
    public enum InquiryStatus {
        BEFORE_ANSWER("대기"),
        ANSWERED("답변완료");

        private final String label;

        InquiryStatus(String label) {
            this.label = label;
        }
    }

}
