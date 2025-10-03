package com.example.movieplatform.movie.controller;

import com.example.movieplatform.movie.dto.SearchResponse;
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
    public ResponseEntity<Movie> getMovieDetail(@PathVariable String docId) {
        Movie movie = movieService.getMovie(docId);

        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<SearchResponse>> search(@RequestParam String keyword){
        List<SearchResponse> searchMovies = movieService.searchMovies(keyword)
                .stream()
                .map(SearchResponse::from)
                .toList();

        return ResponseEntity.ok(searchMovies);
    }
}
