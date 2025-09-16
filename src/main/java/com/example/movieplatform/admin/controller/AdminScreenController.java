package com.example.movieplatform.admin.controller;

import com.example.movieplatform.common.exception.ScreenAlreadyExistsException;
import com.example.movieplatform.screen.entity.Screen;
import com.example.movieplatform.screen.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/screens")
public class AdminScreenController {

    private final ScreenService screenService;

    @GetMapping
    public ResponseEntity<List<Screen>> adminScreens() {

        List<Screen> screens = screenService.getAllScreens();
        return ResponseEntity.ok(screens);
    }

    @PostMapping
    public ResponseEntity<String> addScreen(@RequestParam String screenName) {
        try {
            screenService.createScreen(screenName);
            return ResponseEntity.ok("극장 추가 완료!!");
        } catch (ScreenAlreadyExistsException e) {
            return ResponseEntity
                    .badRequest()
                    .body(screenName + " 은 이미 존재하는 극장 이름입니다.");
        }
    }
}
