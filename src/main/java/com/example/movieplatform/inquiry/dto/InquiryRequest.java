package com.example.movieplatform.inquiry.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InquiryRequest {
    private String title;
    private String content;
    private String inquiryType;
}
