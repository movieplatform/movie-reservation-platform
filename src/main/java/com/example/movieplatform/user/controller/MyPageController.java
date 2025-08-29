package com.example.movieplatform.user.controller;

import groovy.util.logging.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my-page")
@Slf4j
public class MyPageController {

    // 마이페이지 메인
    @GetMapping
    public String myPage() {
        return "mypage/main";
    }


    // 회원정보 페이지
    @GetMapping("/profile")
    public String profilePage() {
        return "mypage/profile";
    }
}
