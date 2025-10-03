package com.example.movieplatform.movie.repository;

import com.example.movieplatform.movie.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, String> {
    Optional<Movie> findByDocId(String docId);
    List<Movie> findAllByOrderByRepRlsDateDesc();
    // 개봉일 기준 내림차순으로 상위 10개 영화 조회
    List<Movie> findTop10ByOrderByRepRlsDateDesc();
    // 장르별 영화 조회 (페이징)
    @Query("SELECT DISTINCT m FROM MovieGenre mg " +
            "JOIN mg.movie m " +
            "JOIN mg.genre g " +
            "WHERE g.name = :genreName " +
            "ORDER BY m.title ASC")  // Movie 기준 정렬
    Page<Movie> findByGenreName(@Param("genreName") String genreName, Pageable pageable);
    // 영화중에서 평점이 가장 높은 영화 10개 조회
    List<Movie> findTop10ByOrderByAverageRatingDesc();
    // 검화 검색
    List<Movie> findTop10ByTitleContainingOrderByRepRlsDateDesc(String keyword);

}
