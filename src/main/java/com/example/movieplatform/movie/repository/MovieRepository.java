package com.example.movieplatform.movie.repository;

import com.example.movieplatform.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, String> {
    Optional<Movie> findByDocId(String docId);
}
