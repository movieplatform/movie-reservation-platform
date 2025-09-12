package com.example.movieplatform.auth.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logout")
public class LogoutController {
    @PostMapping
    public ResponseEntity<String> logout(HttpServletRequest request) {

            request.getSession().invalidate(); // 세션 종료
            SecurityContextHolder.clearContext(); // SecurityContext 초기화
            return ResponseEntity.ok("로그아웃 성공");

    }
}
