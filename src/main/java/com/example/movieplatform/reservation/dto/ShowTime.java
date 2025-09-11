package com.example.movieplatform.reservation.dto;

import lombok.Getter;

import java.time.LocalTime;

// 관리자가 상영 날짜를 지정해서 상영일정을 등록하면 자동으로 9시 12시 18시 22시에 영화 편성(조조는 더싸고 심야는 더 비쌈)
@Getter
public enum ShowTime {
    MORNING(LocalTime.of(9,0), 10000L),
    AFTERNOON(LocalTime.of(13,0), 12000L),
    EVENING(LocalTime.of(18,0), 12000L),
    NIGHT(LocalTime.of(22,0), 14000L);

    private final LocalTime time;
    private final Long price;

    ShowTime(LocalTime time, Long price) {
        this.time = time;
        this.price = price;
    }
}
