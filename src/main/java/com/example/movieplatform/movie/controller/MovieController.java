package com.example.movieplatform.movie.controller;

import com.example.movieplatform.movie.entity.Movie;
import com.example.movieplatform.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/movies")
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<Page<Movie>> movies(@RequestParam(defaultValue = "ALL") String genre,
                                              Pageable pageable) {

            Page<Movie> getAllMovies = movieService.getMoviesByGenre(genre, pageable);
            return ResponseEntity.ok(getAllMovies);

    }

    @GetMapping("/{docId}")
    public String movieDetail(@PathVariable String docId, Model model) {
        Movie movie = movieService.getMovie(docId);
        model.addAttribute("movie", movie);
        return "moviedetail";
    }
}
