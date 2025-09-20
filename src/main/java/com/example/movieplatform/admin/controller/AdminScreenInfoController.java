package com.example.movieplatform.admin.controller;

import com.example.movieplatform.admin.dto.AdminScreenInfoDto;
import com.example.movieplatform.movie.entity.Movie;
import com.example.movieplatform.movie.service.MovieService;
import com.example.movieplatform.reservation.entity.ScreeningInfo;
import com.example.movieplatform.reservation.service.ScreeningInfoService;
import com.example.movieplatform.theater.entity.Screen;
import com.example.movieplatform.theater.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/screenings")
public class AdminScreenInfoController {

    private final MovieService movieService;
    private final ScreenService screenService;
    private final ScreeningInfoService screeningInfoService;

//    @GetMapping
//    public String Screenings(Model model) {
//        List<Movie> recentTop10Movies = movieService.getRecentTop10Movies();
//        List<Screen> screens = screenService.getAllScreens();
//        List<ScreeningInfo> screeningInfos = screeningInfoService.getScreeningInfos();
//        model.addAttribute("recentTop10Movies", recentTop10Movies);
//        model.addAttribute("screens", screens);
//        model.addAttribute("screenInfoDto", new AdminScreenInfoDto());
//        model.addAttribute("today", LocalDate.now().toString());
//        model.addAttribute("screeningInfos", screeningInfos);
//        return "admin/screenings";
//    }

    @PostMapping
    public String Screenings(@ModelAttribute AdminScreenInfoDto adminScreenInfoDto) {
        screeningInfoService.saveScreeningInfo(adminScreenInfoDto);
        return "redirect:/admin/screenings";
    }

    @PostMapping("{id}/delete")
    public String deleteScreeningInfo(@PathVariable Long id) {
        screeningInfoService.deleteScreeningInfo(id);
        return "redirect:/admin/screenings";
    }
}
