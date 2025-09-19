package com.example.movieplatform.movie.entity;

import com.example.movieplatform.movie.dto.ResultWrapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Table(name = "movies")
@AllArgsConstructor
@Getter
public class Movie {
    @Id
    private String docId;
    private String title;
    private String titleEng;
    private Long runtime;  // 상영시간
    private String rating; // 관람등급
    private String genre;
    private String repRlsDate;  // 개봉일
    @Column(length = 1000)
    private String posterUrl;

    private double averageRating;
    public Movie(){};


    public static Movie ofMovie(ResultWrapper result, String validPoster){
        return new Movie(result.DOCID, result.title, result.titleEng, result.runtime, result.rating,
                result.genre, result.repRlsDate, validPoster, 0);
    }

    public void updateAverageRating(double avg){
        this.averageRating = avg;
    }
}