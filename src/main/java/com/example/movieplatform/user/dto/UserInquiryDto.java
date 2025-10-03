package com.example.movieplatform.user.dto;

import com.example.movieplatform.inquiry.entity.Inquiry;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserInquiryDto {
    private Long id;
    private String inquiryTitle;
    private String inquiryContent;
    private LocalDateTime inquiryDateTime;
    private String inquiryType;
    private String inquiryStatus;  // 답변 완료인지 대기인지
    private LocalDateTime answerDateTime;
    private String answerContent;

    public UserInquiryDto(Long id, String inquiryTitle, String inquiryContent, LocalDateTime inquiryDateTime,
                          Inquiry.InquiryType inquiryType, Inquiry.InquiryStatus inquiryStatus,
                          LocalDateTime answerDateTime, String answerContent) {
        this.id = id;
        this.inquiryTitle = inquiryTitle;
        this.inquiryContent = inquiryContent;
        this.inquiryDateTime = inquiryDateTime;
        this.inquiryType = inquiryType.getLabel();   // 여기서 변환
        this.inquiryStatus = inquiryStatus.getLabel();
        this.answerDateTime = answerDateTime;
        this.answerContent = answerContent;
    }
}
