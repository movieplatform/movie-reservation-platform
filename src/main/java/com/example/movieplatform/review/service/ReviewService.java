package com.example.movieplatform.review.service;

import com.example.movieplatform.movie.entity.Movie;
import com.example.movieplatform.movie.repository.MovieRepository;
import com.example.movieplatform.review.dto.ReviewRequest;
import com.example.movieplatform.review.entity.Review;
import com.example.movieplatform.review.repository.ReviewRepository;
import com.example.movieplatform.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// 최신순 평점순
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    // 리뷰 등록
    public void saveReview(ReviewRequest reviewRequest, Movie movie, User user) {
        Review review = Review.ofReview(reviewRequest.getRating(), reviewRequest.getContent(), user, movie);
        reviewRepository.save(review);

        double avg = reviewRepository.findByMovie(movie).stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        movie.updateAverageRating(avg);
        movieRepository.save(movie);

    }

    // 리뷰 최신순
    public List<Review> getReviewsSortedByLatest (Movie movie) {
        return reviewRepository.findByMovieOrderByPostedAtDesc(movie);
    }

    // 리뷰 평점순
    public List<Review> getReviewsSortedByRating(Movie movie){
        return reviewRepository.findByMovieOrderByRatingDesc(movie);
    }

    // 내 리뷰 조회
    public List<Review> getMyReviews(User user) {
        return reviewRepository.findByUserOrderByPostedAtDesc(user);
    }

}
