package com.example.movieplatform.reservation.dto;

import com.example.movieplatform.theater.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SeatDto {
    private long id;
    private String seatNumber;
    private boolean occupied;    // 좌석 점유 여부 (누군가 예약했는지)

    public static SeatDto from(Seat seat, Boolean occupied) {
        return new SeatDto(seat.getId(), seat.getSeatNumber(), occupied);
    }
}
