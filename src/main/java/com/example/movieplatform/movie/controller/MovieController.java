package com.example.movieplatform.movie.controller;

import com.example.movieplatform.movie.entity.Movie;
import com.example.movieplatform.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/movies")
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> movies(Model model) {
        List<Movie> movies = movieService.getMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{docId}")
    public String movieDetail(@PathVariable String docId, Model model) {
        Movie movie = movieService.getMovie(docId);
        model.addAttribute("movie", movie);
        return "moviedetail";
    }
}
