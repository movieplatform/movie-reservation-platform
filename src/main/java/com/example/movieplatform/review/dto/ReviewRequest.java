package com.example.movieplatform.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewRequest {
    @Min(value = 1, message = "평점은 최소 1점 이상이어야 합니다.")
    @Max(value = 5, message = "평점은 최대 5점까지 가능합니다.")
    private int rating; // 평점 1~5

    @NotBlank(message = "내용을 입력하세요.")
    private String content;
}
