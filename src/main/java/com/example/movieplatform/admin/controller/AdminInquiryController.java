package com.example.movieplatform.admin.controller;

import com.example.movieplatform.admin.dto.BeforeAnswerInquiryResponse;
import com.example.movieplatform.auth.dto.UserPrincipal;
import com.example.movieplatform.inquiry.entity.Inquiry;
import com.example.movieplatform.inquiry.service.InquiryService;
import com.example.movieplatform.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/inquiries")
public class AdminInquiryController {

    private final InquiryService inquiryService;

    // 답변 안한 유저들의 문의 목록 출력
    @GetMapping
    public ResponseEntity<List<BeforeAnswerInquiryResponse>> getBeforeAnswerInquiry() {
        List<BeforeAnswerInquiryResponse> response = inquiryService.findByStatus(Inquiry.InquiryStatus.BEFORE_ANSWER)
                .stream()
                .map(BeforeAnswerInquiryResponse::from)
                .toList();

        return ResponseEntity.ok(response);
    }

    // 해당 유저 문의에 관리자가 답변
    @PostMapping
    public ResponseEntity<String> adminInquiryAnswer(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                                     @RequestParam Long inquiryId, @RequestParam String content) {
        User user = userPrincipal.getUser();
        inquiryService.adminAnswerInquiry(user.getId(), inquiryId, content);

        return ResponseEntity.ok("관리자 답변 완료!!");
    }
}
