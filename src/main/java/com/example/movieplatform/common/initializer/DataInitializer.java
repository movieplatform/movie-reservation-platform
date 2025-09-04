package com.example.movieplatform.common.initializer;

import com.example.movieplatform.movie.service.KMDBService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final KMDBService  kmdbService;


    @Override
    public void run(String... args) throws Exception {
        kmdbService.loadMovies();
    }
}
