package com.example.movieplatform.movie.entity;

import com.example.movieplatform.movie.dto.KMDBApiResponse;
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
    @Column(length = 2000)
    private String plot;

    private double averageRating;
    public Movie(){};


    public static Movie ofMovie(KMDBApiResponse.ResultWrapper result, String validPoster, String plot){
        Long runtime = 0L;  // 기본값
        if(result.runtime() != null && !result.runtime().isEmpty()){
            try {
                runtime = Long.valueOf(result.runtime());
            } catch (NumberFormatException e){
                runtime = 0L;  // 혹은 null 처리
            }
        }

        return new Movie(
                result.docid(),
                result.title(),
                result.titleEng(),
                runtime,
                result.rating(),
                result.genre(),
                result.repRlsDate(),
                validPoster,
                plot,
                0
        );
    }

    public void updateAverageRating(double avg){
        this.averageRating = avg;
    }
}