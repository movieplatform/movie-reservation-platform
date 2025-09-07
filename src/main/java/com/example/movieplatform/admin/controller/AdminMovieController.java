package com.example.movieplatform.admin.controller;

import com.example.movieplatform.admin.dto.MovieLoadRequest;
import com.example.movieplatform.admin.service.AdminMovieService;
import com.example.movieplatform.movie.entity.Movie;
import com.example.movieplatform.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/movies")
public class AdminMovieController {

    private final AdminMovieService adminMovieService;
    private final MovieService movieService;

    @GetMapping
    public String adminMovies(Model model) {

        List<Movie> movies = movieService.getMovies();
        model.addAttribute("movies", movies);
        return "admin/adminmovie";
    }

    // 영화 최종족으로 몇개 불러왔는지 메세지 추가
    @PostMapping
    public String loadMovies(@ModelAttribute MovieLoadRequest movieLoadRequest) {
        adminMovieService.loadMovies(movieLoadRequest);

        return "redirect:/admin/movies";
    }

}
