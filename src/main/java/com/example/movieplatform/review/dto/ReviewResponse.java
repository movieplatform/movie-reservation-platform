package com.example.movieplatform.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReviewResponse {
    private int rating;
    private String content;
    private LocalDateTime postedAt;
    private String username;
}
