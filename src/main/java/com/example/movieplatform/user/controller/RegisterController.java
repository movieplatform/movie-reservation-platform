package com.example.movieplatform.user.controller;

import com.example.movieplatform.common.exception.EmailAlreadyExistsException;
import com.example.movieplatform.user.dto.RegisterDto;
import com.example.movieplatform.user.service.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/register")
public class RegisterController {

    private final RegisterService registerService;

    // 회원가입
    @PostMapping
    public ResponseEntity<String> registerProcess(@Valid @RequestBody RegisterDto registerDto) {

        // 이미 존재하는 이메일로 회원 가입할시 예외(GlobalExceptionHandler)
        // valid 검증 실패시 에외
            registerService.register(registerDto);
            return ResponseEntity.ok("회원가입 성공");
    }
}
