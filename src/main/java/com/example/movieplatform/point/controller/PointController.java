package com.example.movieplatform.point.controller;

import com.example.movieplatform.auth.dto.UserPrincipal;
import com.example.movieplatform.point.entity.Point;
import com.example.movieplatform.point.service.PointService;
import com.example.movieplatform.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/my-page")
public class PointController {

    private final PointService pointService;

    @GetMapping("/points")
    public String points(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        User user = userPrincipal.getUser();

        List<Point> pointHistory = pointService.getAllPointsByUserId(user.getId());

        long totalPoint = pointHistory.stream()
                .mapToLong(p -> p.getTransactionType() == Point.TransactionType.EARN ? p.getPointAmount() : -p.getPointAmount())
                .sum();

        model.addAttribute("pointHistory", pointHistory);
        model.addAttribute("totalPoint", totalPoint);
        model.addAttribute("TransactionType", Point.TransactionType.class);

        return "mypage/point";
    }
}
