package com.example.movieplatform.admin.controller;

import com.example.movieplatform.admin.dto.ScreenResponse;
import com.example.movieplatform.common.exception.TheaterAlreadyExistsException;
import com.example.movieplatform.theater.entity.Screen;
import com.example.movieplatform.theater.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/screens")
public class AdminScreenController {

    private final ScreenService screenService;

    @GetMapping("/{theaterId}")
    public ResponseEntity<List<ScreenResponse>> getScreensByTheater(@PathVariable Long theaterId) {
        List<ScreenResponse> screens = screenService.getScreensByTheater(theaterId);
        return ResponseEntity.ok().body(screens);
    }

}
