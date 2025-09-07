package com.example.movieplatform.admin.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class MovieLoadRequest {
    private String genre;
    private String nation;
    private String releaseStart;
    private String releaseEnd;
    private int listCount;
}
