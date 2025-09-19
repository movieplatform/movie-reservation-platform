package com.example.movieplatform.review.controller;

import com.example.movieplatform.auth.dto.UserPrincipal;
import com.example.movieplatform.movie.entity.Movie;
import com.example.movieplatform.movie.service.MovieService;
import com.example.movieplatform.review.dto.ReviewRequest;
import com.example.movieplatform.review.dto.ReviewResponse;
import com.example.movieplatform.review.entity.Review;
import com.example.movieplatform.review.service.ReviewService;
import com.example.movieplatform.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final MovieService movieService;

    @PostMapping("/{docId}")
    public ResponseEntity<String> saveReview(@Valid @RequestBody ReviewRequest reviewRequest,
                                     @AuthenticationPrincipal UserPrincipal userPrincipal,
                                     @PathVariable String docId) {
        User user = userPrincipal.getUser();
        Movie movie = movieService.getMovie(docId);

        reviewService.saveReview(reviewRequest, movie, user);

        return ResponseEntity.ok("리뷰 저장 완료!!");
    }

    // 리뷰 최신순
    @GetMapping("/{docId}/latest")
    public ResponseEntity<List<ReviewResponse>> getLatestReviews(@PathVariable String docId) {
        Movie movie = movieService.getMovie(docId);
        List<ReviewResponse> latestReview = reviewService.getReviewsSortedByLatest(movie);
        return ResponseEntity.ok(latestReview);
    }

    // 리뷰 평점순
    @GetMapping("/{docId}/rating")
    public ResponseEntity<List<ReviewResponse>> getRatingReviews(@PathVariable String docId) {
        Movie movie = movieService.getMovie(docId);
        List<ReviewResponse> ratingReview = reviewService.getReviewsSortedByRating(movie);
        return ResponseEntity.ok(ratingReview);
    }
}
