package com.example.movieplatform.inquiry.controller;

import com.example.movieplatform.inquiry.dto.InquiryRequest;
import com.example.movieplatform.inquiry.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;

    @PostMapping
    public ResponseEntity<String> saveInquiry(@RequestBody InquiryRequest request) {
        inquiryService.saveInquiry(request);
        return ResponseEntity.ok("문의 완료!!");
    }
}
