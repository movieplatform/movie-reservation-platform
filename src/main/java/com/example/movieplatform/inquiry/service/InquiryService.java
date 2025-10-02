package com.example.movieplatform.inquiry.service;

import com.example.movieplatform.inquiry.dto.InquiryRequest;
import com.example.movieplatform.inquiry.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryService {
    private final InquiryRepository inquiryRepository;

    public void saveInquiry(InquiryRequest request) {

    }
}
