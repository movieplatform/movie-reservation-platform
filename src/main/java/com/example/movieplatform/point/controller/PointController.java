package com.example.movieplatform.point.controller;

import com.example.movieplatform.auth.dto.UserPrincipal;
import com.example.movieplatform.point.dto.UserPointResponse;
import com.example.movieplatform.point.dto.UserPointSummary;
import com.example.movieplatform.point.entity.Point;
import com.example.movieplatform.point.service.PointService;
import com.example.movieplatform.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/my-page")
public class PointController {

    private final PointService pointService;

//    단순히 "로그인한 사용자의 아이디/이메일" 정도만 필요하면 → Principal
//    로그인한 유저의 자세한 정보(User 엔티티, 권한, 프로필 등) 가 필요하면 → @AuthenticationPrincipal

    @GetMapping("/points")
    public ResponseEntity<UserPointSummary> points(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        User user = userPrincipal.getUser();
        UserPointSummary response = pointService.getUserPointSummary(user.getId());

        return ResponseEntity.ok(response);
    }
}
