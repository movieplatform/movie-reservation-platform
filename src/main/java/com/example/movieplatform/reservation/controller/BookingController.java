package com.example.movieplatform.reservation.controller;

import com.example.movieplatform.auth.dto.UserPrincipal;
import com.example.movieplatform.reservation.dto.BookingRequest;
import com.example.movieplatform.reservation.dto.ScreenDto;
import com.example.movieplatform.reservation.dto.SeatDto;
import com.example.movieplatform.reservation.service.BookingService;
import com.example.movieplatform.theater.entity.Screen;
import com.example.movieplatform.theater.entity.Seat;
import com.example.movieplatform.theater.service.ScreenService;
import com.example.movieplatform.theater.service.SeatService;
import com.example.movieplatform.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {
    private final ScreenService screenService;
    private final SeatService seatService;
    private final BookingService bookingService;

    @GetMapping("/isLogin")
    public ResponseEntity<String> isLogin() {
        return ResponseEntity.ok("로그인이 필요합니다!!");
    }

    @GetMapping("/screen")
    public ResponseEntity<ScreenDto> getScreenByScreeningInfoId(@RequestParam Long screeningInfoId) {
        Screen screen = screenService.getScreenByScreeningInfoId(screeningInfoId);
        List<Seat> seats = seatService.getSeatsByScreenId(screen.getId());
        Set<Long> occupiedSeatIds = seatService.getOccupiedSeatIds(screeningInfoId);

        List<SeatDto> seatDtoList = seats.stream()
                .map(seat -> SeatDto.from(seat, occupiedSeatIds.contains(seat.getId())))
                .toList();

        ScreenDto screenDto = ScreenDto.from(screen, seatDtoList);

        return ResponseEntity.ok(screenDto);
    }

    // 결제하기 버튼 누르는 순간 이 메서드 호출
    @PostMapping
    public ResponseEntity<Long> saveBookingInfo(@AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody BookingRequest bookingRequest) {
        User user = userPrincipal.getUser();
        Long bookingId = bookingService.saveBookingInfo(user, bookingRequest);
        return ResponseEntity.ok(bookingId);
    }
}
