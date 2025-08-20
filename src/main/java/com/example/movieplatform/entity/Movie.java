package com.example.movieplatform.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    private String MovieCd;
    private String title;
    private String englishTitle;
    private int releaseYear;
    private LocalDateTime runningTime;
    private String watchGrade;
}