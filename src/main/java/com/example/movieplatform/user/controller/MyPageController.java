package com.example.movieplatform.user.controller;

import com.example.movieplatform.auth.dto.UserPrincipal;
import com.example.movieplatform.review.dto.ReviewResponse;
import com.example.movieplatform.review.entity.Review;
import com.example.movieplatform.review.service.ReviewService;
import com.example.movieplatform.user.dto.UserProfileDto;
import com.example.movieplatform.user.entity.User;
import com.example.movieplatform.user.service.UserService;
import groovy.util.logging.Slf4j;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/my-page")
public class MyPageController {

    private final UserService userService;
    private final ReviewService reviewService;

    // 회원정보 페이지
    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> profilePage(@AuthenticationPrincipal UserPrincipal userPrincipal) {

        User user = userPrincipal.getUser();
        UserProfileDto userProfileDto = UserProfileDto.fromEntity(user);

        return ResponseEntity.ok(userProfileDto);
    }


    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                           HttpServletRequest request, HttpServletResponse response){

        User user = userPrincipal.getUser();
        userService.withdrawUser(user.getId());

        // 로그아웃 처리
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return ResponseEntity.ok("회원탈퇴 완료!");
    }


    // 디티오 만들어서 반환
    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewResponse>> reviews(@AuthenticationPrincipal UserPrincipal userPrincipal){
        User user = userPrincipal.getUser();
        List<ReviewResponse> myReviews = reviewService.getMyReviews(user);
        return ResponseEntity.ok(myReviews);
    }
}
