package com.example.movieplatform.reservation.repository;

import com.example.movieplatform.reservation.entity.ScreeningInfo;
import com.example.movieplatform.screen.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ScreeningInfoRepository extends JpaRepository<ScreeningInfo, Long> {
    boolean existsByScreenAndScreeningDateAndStartTime(Screen screen, LocalDate screeningDate, LocalTime startTime);
    List<ScreeningInfo> findByScreeningDate(LocalDate screeningDate);
}
