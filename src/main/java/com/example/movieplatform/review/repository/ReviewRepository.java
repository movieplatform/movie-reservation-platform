package com.example.movieplatform.review.repository;

import com.example.movieplatform.movie.entity.Movie;
import com.example.movieplatform.review.entity.Review;
import com.example.movieplatform.user.dto.UserReviewDto;
import com.example.movieplatform.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 특정 영화 리뷰를 최신순으로 조회
    List<Review> findByMovieOrderByPostedAtDesc(Movie movie);

    // 특정 영화 리뷰를 평점순으로 조회
    List<Review> findByMovieOrderByRatingDesc(Movie movie);

    // 특정 유저의 모든 리뷰 최신순(마이페이지)
    List<Review> findByUserOrderByPostedAtDesc(User user);

    List<Review> findByMovie(Movie movie);

    @Query("""
        SELECT new com.example.movieplatform.user.dto.UserReviewDto(
            r.id,
            m.posterUrl,
            m.title,
            r.rating,
            r.content,
            r.postedAt
        )
        FROM Review r
        JOIN r.movie m
        WHERE r.user.id = :userId
        ORDER BY r.postedAt DESC
    """)
    List<UserReviewDto> findReviewsByUserId(@Param("userId") Long userId);
}
