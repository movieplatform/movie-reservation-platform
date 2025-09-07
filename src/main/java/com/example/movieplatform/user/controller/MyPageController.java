package com.example.movieplatform.user.controller;

import com.example.movieplatform.user.dto.UserProfileDto;
import com.example.movieplatform.user.entity.User;
import com.example.movieplatform.user.service.UserService;
import groovy.util.logging.Slf4j;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/my-page")
public class MyPageController {

    private final UserService userService;

    // 마이페이지 메인
    @GetMapping
    public String myPage() {
        return "mypage/main";
    }


    // 회원정보 페이지
    @GetMapping("/profile")
    public String profilePage(Principal principal, Model model) {

        User user = userService.findUserByEmail(principal.getName());
        UserProfileDto userProfileDto = UserProfileDto.fromEntity(user);

        model.addAttribute("userProfile", userProfileDto);

        return "mypage/profile";
    }

    @PostMapping("/withdraw")
    public String withdraw(HttpServletRequest request, HttpServletResponse response, Principal principal){

        User user = userService.findUserByEmail(principal.getName());
        userService.withdrawUser(user.getId());

        // 로그아웃 처리
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout";
    }
}
