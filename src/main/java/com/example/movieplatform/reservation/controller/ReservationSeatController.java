package com.example.movieplatform.reservation.controller;

import com.example.movieplatform.reservation.dto.SeatDto;
import com.example.movieplatform.theater.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationSeatController {
    private final SeatService seatService;

    @GetMapping("/seats")
    public ResponseEntity<List<SeatDto>> getAllSeats(@RequestParam Long screeningInfoId) {
        List<SeatDto> response = seatService.getSeatsByScreeningInfoId(screeningInfoId);
        return ResponseEntity.ok(response);
    }
}
