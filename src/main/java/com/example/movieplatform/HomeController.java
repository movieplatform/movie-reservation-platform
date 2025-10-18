package com.example.movieplatform;

import com.example.movieplatform.movie.dto.MovieResponse;
import com.example.movieplatform.movie.entity.Movie;
import com.example.movieplatform.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/home")
public class HomeController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getTop10RatedMovies() {
        List<Movie> movies = movieService.getTop10RatedMovies();

        Movie dummy = new Movie();
        dummy.setDocId("123");
        dummy.setTitle("Dummy Test Movie444");
        dummy.setRating("3.0");
        movies.add(dummy);

        List<MovieResponse> response = movies
                .stream()
                .map(MovieResponse::from)
                .toList();

        return ResponseEntity.ok(response);
    }
}
