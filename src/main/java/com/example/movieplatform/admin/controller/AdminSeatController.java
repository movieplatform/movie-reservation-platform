package com.example.movieplatform.admin.controller;

import com.example.movieplatform.theater.entity.Screen;
import com.example.movieplatform.theater.service.ScreenService;
import com.example.movieplatform.theater.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/seat")
public class AdminSeatController {

    private final ScreenService screenService;
    private final SeatService seatService;

    @GetMapping("/{id}")
    public ResponseEntity<Long> screenSeat(@PathVariable Long id) {
        Screen screen = screenService.getScreenById(id);
        Long seatCount = seatService.countByScreen(screen);

        return ResponseEntity.ok(seatCount);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> addSeat(@PathVariable Long id, @RequestParam int rows, @RequestParam int cols){
        try{
            Screen screen = screenService.getScreenById(id);
            seatService.createSeatsForScreen(screen, rows, cols);
            return ResponseEntity.ok("좌석 생성 완료");
        } catch(IllegalArgumentException | IllegalStateException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(500).body("서버 오류 발생");
        }
    }
}
