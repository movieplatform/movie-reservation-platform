package com.example.movieplatform.admin.controller;

import com.example.movieplatform.admin.dto.ScreenInfoResponse;
import com.example.movieplatform.reservation.service.ScreeningInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/screenings")
public class AdminScreenInfoController {

    private final ScreeningInfoService screeningInfoService;

    @PostMapping
    public ResponseEntity<String> createScreenInfo(@RequestParam Long screenId) {
        try{
            screeningInfoService.saveScreeningInfo(screenId);
            return ResponseEntity.ok("상영일정 저장 완료!!");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{theaterId}")
    public ResponseEntity<ScreenInfoResponse> getScreenInfo(@PathVariable Long theaterId) {
        ScreenInfoResponse response = screeningInfoService.getScreenInfoByTheaterId(theaterId);
        return ResponseEntity.ok(response);
    }

}
