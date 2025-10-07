package com.example.movieplatform.movie.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record KMDBApiResponse(
        @JsonProperty("Data")
        List<DataWrapper> data) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record DataWrapper(
            @JsonProperty("Result")
            List<ResultWrapper> result) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ResultWrapper(
            @JsonProperty("DOCID") String docid,
            @JsonProperty("title") String title,
            @JsonProperty("titleEng") String titleEng,
            @JsonProperty("runtime") String runtime,
            @JsonProperty("rating") String rating,
            @JsonProperty("genre") String genre,
            @JsonProperty("repRlsDate") String repRlsDate,
            @JsonProperty("posters") String posters,
            @JsonProperty("plots") Plots plots
    ) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Plots(
            @JsonProperty("plot")
            List<Plot> plot) {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Plot(
            @JsonProperty("plotLang") String plotLang,
            @JsonProperty("plotText") String plotText

    ) {
    }
}
