package com.example.movieplatform.review.repository;

import com.example.movieplatform.movie.entity.Movie;
import com.example.movieplatform.review.entity.Review;
import com.example.movieplatform.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 특정 영화 리뷰를 최신순으로 조회
    List<Review> findByMovieOrderByPostedAtDesc(Movie movie);

    // 특정 영화 리뷰를 평점순으로 조회
    List<Review> findByMovieOrderByRatingDesc(Movie movie);

    // 특정 유저의 모든 리뷰 최신순(마이페이지)
    List<Review> findByUserOrderByPostedAtDesc(User user);

    List<Review> findByMovie(Movie movie);
}
