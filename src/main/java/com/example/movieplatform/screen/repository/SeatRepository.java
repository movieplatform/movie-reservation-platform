package com.example.movieplatform.screen.repository;

import com.example.movieplatform.screen.entity.Screen;
import com.example.movieplatform.screen.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    boolean existsByScreen(Screen screen);
}
