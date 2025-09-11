package com.example.movieplatform.reservation.entity;

import com.example.movieplatform.movie.entity.Movie;
import com.example.movieplatform.screen.entity.Screen;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "screening_info")
@Getter
public class ScreeningInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate screeningDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long price;  // 시간대별 가격 측정

    @Enumerated(EnumType.STRING)
    private ScreeningStatus screeningStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_movieCd")
    private Movie movie;

    public ScreeningInfo(){}

    public ScreeningInfo(LocalDate screeningDate, LocalTime startTime, LocalTime endTime,
                         Long price, ScreeningStatus screeningStatus, Screen screen, Movie movie) {
        this.screeningDate = screeningDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.screeningStatus = screeningStatus;
        this.screen = screen;
        this.movie = movie;
    }

    public enum ScreeningStatus{
        SCHEDULED,   // 상영 예정
        ONGOING,     // 현재 상영 중
        FINISHED     // 종료
    }
}
