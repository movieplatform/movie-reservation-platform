package com.example.movieplatform.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AdminScreenInfoDto {
    private String docId;    // 선택한 영화 ID
    private Long screenId;    // 선택한 상영관 ID
    private LocalDate startDate; // 상영 시작일
    private LocalDate endDate;   // 상영 종료일
}
