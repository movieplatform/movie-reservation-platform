package com.example.movieplatform.movie.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Plots {
    public List<Plot> plot;
}
