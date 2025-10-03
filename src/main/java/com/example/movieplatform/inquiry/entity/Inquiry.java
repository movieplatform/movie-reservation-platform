package com.example.movieplatform.inquiry.entity;

import com.example.movieplatform.inquiry.dto.InquiryRequest;
import com.example.movieplatform.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Arrays;

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
    @Enumerated(EnumType.STRING)
    private InquiryType inquiryType;
    @Enumerated(EnumType.STRING)
    private InquiryStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Inquiry() {}
    public Inquiry(String title, String content, InquiryType inquiryType, User user) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.inquiryType = inquiryType;
        this.status = InquiryStatus.BEFORE_ANSWER;
        this.user = user;
    }

    public static Inquiry ofInquiry(InquiryRequest request, User user) {
        InquiryType inquiryType = InquiryType.fromLabel(request.getInquiryType());
        return new Inquiry(request.getTitle(), request.getContent(), inquiryType, user);
    }

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

        public static InquiryType fromLabel(String label) {
            return Arrays.stream(values())
                    .filter(type -> type.label.equals(label))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Unknown inquiryType: " + label));
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

    public void updateInquiryStatus(InquiryStatus status) {
        this.status = status;
    }

}
