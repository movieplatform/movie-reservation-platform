package com.example.movieplatform.reservation.controller;

import com.example.movieplatform.reservation.entity.ScreeningInfo;
import com.example.movieplatform.reservation.service.ScreeningInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ScreeningInfoService screeningInfoService;

    @GetMapping
    public String reservations(Model model) {
        return "reservation";
    }

    @GetMapping("/{date}")
    public String reservation(@PathVariable(required = false) LocalDate date, Model model) {
        LocalDate today = LocalDate.now();
        List<LocalDate> dates = new ArrayList<>();
        for(int i = 0; i<7; i++){
            dates.add(today.plusDays(i));
        }

        LocalDate screeningDate = (date != null) ? date : today;

        List<ScreeningInfo> infos = screeningInfoService.getScreeningInfosByScreeningDate(screeningDate);

        model.addAttribute("dates", dates);
        model.addAttribute("screeningDate", screeningDate);
        model.addAttribute("infos", infos);

        return "reservation";
    }
}
