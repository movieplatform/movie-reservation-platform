package com.example.movieplatform.point.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserPointSummary {
    private List<UserPointResponse> points;
    private long total;
}
