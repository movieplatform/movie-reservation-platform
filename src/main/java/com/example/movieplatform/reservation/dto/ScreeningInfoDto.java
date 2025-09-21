package com.example.movieplatform.reservation.dto;

import com.example.movieplatform.reservation.entity.ScreeningInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class ScreeningInfoDto {
    private LocalDate screeningDate;
    private String screenName;
    private String movieTitle;
    private LocalTime startTime;
    private LocalTime endTime;
    private ScreeningInfo.ScreeningStatus screeningStatus;

    public String getStatus() {
        return screeningStatus.toString();
    }
}
