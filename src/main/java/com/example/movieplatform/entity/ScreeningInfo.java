package com.example.movieplatform.entity;

import com.example.movieplatform.movie.entity.Movie;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "screening_info")
public class ScreeningInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate screeningDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_movieCd")
    private Movie movie;
}
