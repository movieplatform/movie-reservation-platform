package com.example.movieplatform.inquiry.controller;

import com.example.movieplatform.auth.dto.UserPrincipal;
import com.example.movieplatform.inquiry.dto.InquiryRequest;
import com.example.movieplatform.inquiry.service.InquiryService;
import com.example.movieplatform.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<String> saveInquiry(@AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody InquiryRequest request) {
        User user = userPrincipal.getUser();
        inquiryService.saveInquiry(request, user);

        return ResponseEntity.ok("문의 완료!!");
    }
}
