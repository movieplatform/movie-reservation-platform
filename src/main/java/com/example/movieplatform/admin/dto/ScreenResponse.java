package com.example.movieplatform.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScreenResponse {
    private Long id;
    private String screenName;
    private TheaterResponse theater;
}
