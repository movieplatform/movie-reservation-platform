package com.example.movieplatform.point.controller;

import com.example.movieplatform.auth.dto.UserPrincipal;
import com.example.movieplatform.point.dto.UserPointResponse;
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
    public ResponseEntity<Map<String, Object>> points(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        User user = userPrincipal.getUser();

        List<Point> pointHistory = pointService.getAllPointsByUserId(user.getId());

        List<UserPointResponse> points = pointHistory.stream().map(p -> new UserPointResponse(
                p.getTransactionDate(),    // 적립 또는 사용한 날짜
                p.getTransactionType().getDescription(),     //  적립인지 사용인지
                p.getPointReason().getDescription(),   // 사유
                p.getTransactionType() == Point.TransactionType.EARN ? p.getPointAmount() : -p.getPointAmount()  // 예) 적립이면 +500 사용이면 -500
        )).toList();

        // 총 포인트
        long totalPoint = pointHistory.stream()
                .mapToLong(p -> p.getTransactionType() == Point.TransactionType.EARN ? p.getPointAmount() : -p.getPointAmount())
                .sum();

        Map<String, Object> result = Map.of(
                "points", points,
                "total", totalPoint
        );

        return ResponseEntity.ok(result);
    }
}
