package com.example.movieplatform.admin.controller;

import com.example.movieplatform.user.entity.User;
import com.example.movieplatform.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/users")
public class AdminUserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    @PostMapping("{id}/status")
    public ResponseEntity<String> withdrawUser(@PathVariable Long id) {
        userService.withdrawUser(id);
        return ResponseEntity.ok("탈퇴 처리 완료");
    }

}
