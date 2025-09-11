package com.example.movieplatform.movie.repository;

import com.example.movieplatform.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, String> {
    Optional<Movie> findByDocId(String docId);
    List<Movie> findAllByOrderByRepRlsDateDesc();
    // 개봉일 기준 내림차순으로 상위 10개 영화 조회
    List<Movie> findTop10ByOrderByRepRlsDateDesc();
}
