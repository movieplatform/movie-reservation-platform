package com.example.movieplatform.theater.service;

import com.example.movieplatform.theater.entity.Screen;
import com.example.movieplatform.theater.entity.Seat;
import com.example.movieplatform.theater.repository.ScreenRepository;
import com.example.movieplatform.theater.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final ScreenRepository screenRepository;
    private final SeatRepository seatRepository;

    // 관리자가 행과 열 지정해서 좌석 생성가능(행 1~8 열 1~10 까지 가능)
    public void createSeatsForScreen(Screen screen, int rows, int cols) {
        if ( rows <1 || rows > 8) throw new IllegalArgumentException("행은 1~8까지 가능합니다.");
        if ( cols <1 || cols > 10) throw new IllegalArgumentException("열은 1~10까지 가능합니다. ");
        if (seatRepository.existsByScreen(screen)) throw new IllegalStateException("이미 좌석이 생성된 상영관입니다.");

        List<Seat> seats = new ArrayList<>();

        for(int i = 0; i < rows; i++) {
            char rowChar = (char) ('A' + i);
            for(int j = 1; j <= cols; j++) {
                String seatNumber = rowChar+ String.valueOf(j);
                Seat seat = new Seat(seatNumber, screen);
                seats.add(seat);
            }
        }
        seatRepository.saveAll(seats);

        screen.updateSeatSize(rows, cols);
        screenRepository.save(screen);
    }

    public Long countByScreen(Screen screen) {
        return seatRepository.countByScreen(screen);
    }
}
