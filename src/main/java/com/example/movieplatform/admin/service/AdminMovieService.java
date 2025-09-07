package com.example.movieplatform.admin.service;

import com.example.movieplatform.admin.dto.MovieLoadRequest;
import com.example.movieplatform.movie.service.KMDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminMovieService {

    private final KMDBService kmdbService;

    public void loadMovies(MovieLoadRequest movieLoadRequest) {
        kmdbService.loadMovies(movieLoadRequest);
    }
}
