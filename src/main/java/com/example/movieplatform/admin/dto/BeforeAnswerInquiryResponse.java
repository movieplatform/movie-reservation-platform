package com.example.movieplatform.admin.dto;

import com.example.movieplatform.inquiry.entity.Inquiry;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BeforeAnswerInquiryResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String inquiryType;
    private String userName;

    public static BeforeAnswerInquiryResponse from(Inquiry inquiry) {
        return new BeforeAnswerInquiryResponse(
                inquiry.getId(),
                inquiry.getTitle(),
                inquiry.getContent(),
                inquiry.getCreatedAt(),
                inquiry.getInquiryType().getLabel(),
                inquiry.getUser().getName()
        );
    }
}
