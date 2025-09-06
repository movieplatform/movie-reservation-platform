package com.example.movieplatform.admin.controller;

import com.example.movieplatform.user.entity.User;
import com.example.movieplatform.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/users")
public class AdminUserController {

    private final UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);

        return "admin/users";
    }

    @PostMapping("{id}/status")
    public String withdrawUser(@PathVariable Long id) {
        userService.withdrawUser(id);
        return "redirect:/admin/users";
    }

}
