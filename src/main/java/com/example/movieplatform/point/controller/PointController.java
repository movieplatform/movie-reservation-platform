package com.example.movieplatform.point.controller;

import com.example.movieplatform.point.service.PointService;
import com.example.movieplatform.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/my-page")
public class PointController {
    private final PointService pointService;

    @GetMapping("/points")
    public String points(@AuthenticationPrincipal User user){
        return "mypage/point";
    }
}
