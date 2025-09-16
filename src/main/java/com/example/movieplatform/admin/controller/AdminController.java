package com.example.movieplatform.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/admin")
public class AdminController {

    // 관리자 홈
    @GetMapping("/check")
    public ResponseEntity<Void> checkAdmin() {
        return ResponseEntity.ok().build();
    }
}
