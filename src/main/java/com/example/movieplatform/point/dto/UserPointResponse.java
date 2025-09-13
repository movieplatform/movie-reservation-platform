package com.example.movieplatform.point.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
public class UserPointResponse {
    private LocalDateTime date;
    private String type;
    private String reason;
    private long amount;
}
