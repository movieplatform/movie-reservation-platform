package com.example.movieplatform.admin.dto;

import com.example.movieplatform.theater.entity.Theater;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TheaterResponse {
    private Long id;
    private String theaterName;

    public static TheaterResponse from(Theater theater) {
        return new TheaterResponse(
                theater.getId(),
                theater.getTheaterName()
        );
    }
}
