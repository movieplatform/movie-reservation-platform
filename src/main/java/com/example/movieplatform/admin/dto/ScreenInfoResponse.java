package com.example.movieplatform.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ScreenInfoResponse {
    private String theaterName;
    private List<ScreeningInfo> dates;

    @Getter
    @AllArgsConstructor
    public static class ScreeningInfo {
        private LocalDate date;   // 상영일
        private List<ScreenDto> screens;
    }

    @Getter
    @AllArgsConstructor
    public static class ScreenDto {
        private String screenName;  // 싱양관 이름
        private List<MovieDto> movies;
    }

    @Getter
    @AllArgsConstructor
    public static class MovieDto {
        private String title;
        private LocalTime startTime;
        private LocalTime endTime;
        private String status;
    }
}
