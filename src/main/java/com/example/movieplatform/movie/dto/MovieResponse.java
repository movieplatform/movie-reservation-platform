package com.example.movieplatform.movie.dto;

import com.example.movieplatform.movie.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MovieResponse {
    private String docId;
    private String title;
    private String rating;  // 상영등급 변환필요
    private Long runtime;
    private String repRlsDate;
    private String posterUrl;

    public static MovieResponse from(Movie movie) {
        return new MovieResponse(
                movie.getDocId(),
                movie.getTitle(),
                mapRating(movie.getRating()),
                movie.getRuntime(),
                movie.getRepRlsDate(),
                movie.getPosterUrl()
        );
    }

    private static String mapRating(String rating){
        if(rating.startsWith("전체")) return "전체";
        if(rating.startsWith("12")) return "12";
        if(rating.startsWith("15")) return "15";
        if(rating.startsWith("19")||rating.startsWith("청소년")) return "19";
        return "전체";
    }
}
