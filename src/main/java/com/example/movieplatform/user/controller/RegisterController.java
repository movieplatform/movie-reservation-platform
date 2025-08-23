package com.example.movieplatform.user.controller;

import com.example.movieplatform.user.dto.RegisterDto;
import com.example.movieplatform.user.service.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private final RegisterService registerService;

    // 회원가입 페이지로 이동
    @GetMapping
    public String registerP(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "register";
    }

    // 회원가입 후 로그인 페이지로 리다이렉트
    @PostMapping
    public String registerProcess(@Valid RegisterDto registerDto,  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "register";
        }
        registerService.register(registerDto);
        return "redirect:/login";
    }
}
