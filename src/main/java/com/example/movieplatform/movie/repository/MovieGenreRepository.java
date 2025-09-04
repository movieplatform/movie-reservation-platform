package com.example.movieplatform.movie.repository;

import com.example.movieplatform.movie.entity.Genre;
import com.example.movieplatform.movie.entity.Movie;
import com.example.movieplatform.movie.entity.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, Long> {
    Optional<MovieGenre> findByMovieAndGenre(Movie movie, Genre genre);
}
