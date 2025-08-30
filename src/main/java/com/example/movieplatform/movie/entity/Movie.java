package com.example.movieplatform.movie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    public String movieId;
    public String title;
    public String titleEng;
    public String plot;  // 줄거리
    public Long runtime;  // 상영시간
    public String rating; // 관람등급
    public String genre;
    public String repRlsDate;  // 개봉일
    public String posterUrl;
}