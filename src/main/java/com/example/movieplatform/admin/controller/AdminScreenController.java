package com.example.movieplatform.admin.controller;

import com.example.movieplatform.screen.entity.Screen;
import com.example.movieplatform.screen.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/screens")
public class AdminScreenController {

    private final ScreenService screenService;

    @GetMapping
    public String adminScreens(Model model) {

        List<Screen> screens = screenService.getAllScreens();
        model.addAttribute("screens", screens);
        return "admin/screens";
    }

    @PostMapping
    public String addScreen(@RequestParam String screenName) {
        screenService.createScreen(screenName);
        return "redirect:/admin/screens";
    }

}
