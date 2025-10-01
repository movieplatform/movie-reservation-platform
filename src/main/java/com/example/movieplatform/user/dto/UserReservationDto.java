package com.example.movieplatform.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
public class UserReservationDto {
    private Long bookingId;
    private Long totalPrice;
    private String movieTitle;
    private String posterUrl;
    private String theaterName;
    private LocalDate screeningDate;
    private LocalTime startTime;
    private String screenName;

    // 서비스 단에서 가공 디비에서 컬렉션 매핑 안됨
    private String seatSummary;
    private String ticketSummary;
    private int ticketCount;
    private long userPoint;
}
