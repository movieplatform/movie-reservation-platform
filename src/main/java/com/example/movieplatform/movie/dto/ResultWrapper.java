package com.example.movieplatform.movie.dto;

import java.util.List;

public class ResultWrapper {
    public String DOCID;
    public String title;
    public String titleEng;
    public Plots plots;  // 줄거리
    public Long runtime;  // 상영시간
    public String rating; // 관람등급
    public String genre;
    public String repRlsDate;  // 개봉일
    public String posters;
}
