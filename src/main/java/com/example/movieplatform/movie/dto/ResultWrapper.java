package com.example.movieplatform.movie.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultWrapper {
    public String DOCID;
    public String title;
    public String titleEng;
    public Long runtime;  // 상영시간
    public String rating; // 관람등급
    public String genre;
    public String repRlsDate;  // 개봉일
    public String posters;
}
