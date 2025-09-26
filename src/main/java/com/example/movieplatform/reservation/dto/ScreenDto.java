package com.example.movieplatform.reservation.dto;

import com.example.movieplatform.theater.entity.Screen;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ScreenDto {
    private Long ScreenId;
    private String ScreenName;
    private int rows;
    private int cols;
    private List<SeatDto> seats;

    public static ScreenDto from(Screen screen, List<SeatDto> seats) {
        return new ScreenDto(
                screen.getId(),
                screen.getScreenName(),
                screen.getRows(),
                screen.getCols(),
                seats
        );
    }
}
