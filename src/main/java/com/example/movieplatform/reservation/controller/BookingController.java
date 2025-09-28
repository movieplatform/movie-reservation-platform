package com.example.movieplatform.reservation.controller;

import com.example.movieplatform.reservation.dto.ScreenDto;
import com.example.movieplatform.reservation.dto.SeatDto;
import com.example.movieplatform.theater.entity.Screen;
import com.example.movieplatform.theater.entity.Seat;
import com.example.movieplatform.theater.service.ScreenService;
import com.example.movieplatform.theater.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {
    private final ScreenService screenService;
    private final SeatService seatService;

    @GetMapping("/screen")
    public ResponseEntity<ScreenDto> getScreenByScreeningInfoId(@RequestParam Long screeningInfoId) {
        Screen screen = screenService.getScreenByScreeningInfoId(screeningInfoId);
        List<Seat> seats = seatService.getSeatsByScreen(screen);
        Set<Long> occupiedSeatIds = seatService.getOccupiedSeatIds(screeningInfoId);

        List<SeatDto> seatDtoList = seats.stream()
                .map(seat -> SeatDto.from(seat, occupiedSeatIds.contains(seat.getId())))
                .toList();

        ScreenDto screenDto = ScreenDto.from(screen, seatDtoList);

        return ResponseEntity.ok(screenDto);
    }

}
