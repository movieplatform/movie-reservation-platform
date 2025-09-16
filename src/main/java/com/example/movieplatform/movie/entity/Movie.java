package com.example.movieplatform.movie.entity;

import com.example.movieplatform.movie.dto.ResultWrapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@AllArgsConstructor
@Getter
public class Movie {
    @Id
    public String docId;
    public String title;
    public String titleEng;
    public Long runtime;  // 상영시간
    public String rating; // 관람등급
    public String genre;
    public String repRlsDate;  // 개봉일
    @Column(length = 1000)
    public String posterUrl;

    public Movie(){};


    public static Movie ofMovie(ResultWrapper result, String validPoster){
        return new Movie(result.DOCID, result.title, result.titleEng, result.runtime, result.rating,
                result.genre, result.repRlsDate, validPoster);
    }
}