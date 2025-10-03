package com.example.movieplatform.movie.dto;

import com.example.movieplatform.movie.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchResponse {
    private String docId;
    private String title;

    public static SearchResponse from(Movie movie) {
        return new  SearchResponse(movie.getDocId(), movie.getTitle());
    }
}
