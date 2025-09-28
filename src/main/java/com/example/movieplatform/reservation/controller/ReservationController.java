package com.example.movieplatform.reservation.controller;

import com.example.movieplatform.admin.dto.TheaterResponse;
import com.example.movieplatform.movie.dto.MovieResponse;
import com.example.movieplatform.movie.service.MovieService;
import com.example.movieplatform.reservation.dto.ScreeningInfoDto;
import com.example.movieplatform.reservation.service.ScreeningInfoService;
import com.example.movieplatform.theater.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationController {
    private final TheaterService theaterService;
    private final MovieService movieService;
    private final ScreeningInfoService screeningInfoService;
    // 지역선택은 고정
    // 극장선택
    @GetMapping("/theaters")
    public ResponseEntity<List<TheaterResponse>> getAllTheaters(){
         List<TheaterResponse> response = theaterService.getAllTheaters()
                 .stream()
                 .map(TheaterResponse::from)
                 .toList();
         return ResponseEntity.ok(response);
    }

    // 영화선택
    // 상태가 상영전인 영화만 나열
    @GetMapping("/movies")
    public ResponseEntity<List<MovieResponse>> getScheduledMovies(){
        List<MovieResponse> response = screeningInfoService.findAllScheduledMovies()
                .stream()
                .map(MovieResponse::from)
                .toList();
        return ResponseEntity.ok(response);
    }

    // 조건에 맞는 상영정보 나열 - 상영상태가 상영전인거만
    @GetMapping("/screenInfo")
    public ResponseEntity<List<ScreeningInfoDto>> getScreenInfo(@RequestParam Long theaterId,
                                                          @RequestParam String docId,
                                                          @RequestParam LocalDate screeningDate){
        List<ScreeningInfoDto> response = screeningInfoService.reservationScreeningInfo(theaterId,docId,screeningDate)
                .stream()
                .map(ScreeningInfoDto::from)
                .toList();
        return ResponseEntity.ok(response);
    }
}
