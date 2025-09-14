package com.example.movieplatform.movie.service;

import com.example.movieplatform.common.exception.EntityNotFoundException;
import com.example.movieplatform.movie.repository.MovieRepository;
import com.example.movieplatform.movie.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> getMovies() {
        return movieRepository.findAllByOrderByRepRlsDateDesc();
    }

    public Page<Movie> getMoviesByGenre(String genreName, Pageable pageable){
        if (genreName.equals("ALL")) {
            return movieRepository.findAll(pageable);
        }
        return movieRepository.findByGenreName(genreName, pageable);
    }

    public Movie getMovie(String docId) {
        return movieRepository.findByDocId(docId)
                .orElseThrow(() -> new EntityNotFoundException(docId));
    }

    public List<Movie> getRecentTop10Movies() {
        return movieRepository.findTop10ByOrderByRepRlsDateDesc();
    }
}
