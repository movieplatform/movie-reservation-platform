package com.example.movieplatform.admin.controller;

import com.example.movieplatform.screen.entity.Screen;
import com.example.movieplatform.screen.service.ScreenService;
import com.example.movieplatform.screen.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/screens")
public class AdminSeatController {

    private final ScreenService screenService;
    private final SeatService seatService;

    @GetMapping("/{id}")
    public String screenSeat(@PathVariable Long id, Model model) {
        Screen screen = screenService.getScreenById(id);
        Long seatCount = seatService.countByScreen(screen);
        model.addAttribute("screen", screen);
        model.addAttribute("seatCount", seatCount);
        return "admin/seats";
    }

    @PostMapping("/{id}")
    public String addSeat(@PathVariable Long id, @RequestParam int rows, @RequestParam int cols){
        Screen screen = screenService.getScreenById(id);
        seatService.createSeatsForScreen(screen, rows, cols);
        return "redirect:/admin/screens";
    }
}
