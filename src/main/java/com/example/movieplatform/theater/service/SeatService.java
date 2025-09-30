package com.example.movieplatform.theater.service;

import com.example.movieplatform.reservation.dto.SeatDto;
import com.example.movieplatform.reservation.entity.ScreeningInfo;
import com.example.movieplatform.reservation.repository.BookingSeatRepository;
import com.example.movieplatform.reservation.repository.ScreeningInfoRepository;
import com.example.movieplatform.theater.entity.Screen;
import com.example.movieplatform.theater.entity.Seat;
import com.example.movieplatform.theater.repository.ScreenRepository;
import com.example.movieplatform.theater.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final ScreenRepository screenRepository;
    private final ScreeningInfoRepository screeningInfoRepository;
    private final SeatRepository seatRepository;
    private final BookingSeatRepository bookingSeatRepository;

    // 관리자가 행과 열 지정해서 좌석 생성가능(행 1~8 열 1~10 까지 가능)
    @Transactional
    public void createSeatsForScreen(Screen screen, int rows, int cols) {
        if ( rows <1 || rows > 8) throw new IllegalArgumentException("행은 1~8까지 가능합니다.");
        if ( cols <1 || cols > 10) throw new IllegalArgumentException("열은 1~10까지 가능합니다. ");
        if (seatRepository.existsByScreen(screen)) throw new IllegalStateException("이미 좌석이 생성된 상영관입니다.");

        List<Seat> seats = new ArrayList<>();

        for(int i = 0; i < rows; i++) {
            char rowChar = (char) ('A' + i);
            for(int j = 1; j <= cols; j++) {
                String seatNumber = rowChar+ String.valueOf(j);
                Seat seat = new Seat(seatNumber, i+1, j, screen);
                seats.add(seat);
            }
        }
        seatRepository.saveAll(seats);

        screen.updateSeatSize(rows, cols);
        screenRepository.save(screen);
    }

    @Transactional(readOnly = true)
    public Long countByScreen(Screen screen) {
        return seatRepository.countByScreen(screen);
    }

    @Transactional(readOnly = true)
    public List<Seat> getSeatsByScreenId(Long screenId) {
        return seatRepository.findByScreenId(screenId);
    }

    @Transactional(readOnly = true)
    public Set<Long> getOccupiedSeatIds(Long screeningInfoId) {
        List<Long> occupiedIds = bookingSeatRepository.findOccupiedSeatByScreeningInfoId(screeningInfoId);
        return new HashSet<>(occupiedIds);
    }
}
