package com.example.movieplatform.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserReviewDto {
    private Long id;
    private String moviePosterUrl;
    private String movieTitle;
    private int rating;
    private String reviewComment;
    private LocalDateTime postedAt;
}
