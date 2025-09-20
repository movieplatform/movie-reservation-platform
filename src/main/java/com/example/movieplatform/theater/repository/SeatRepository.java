package com.example.movieplatform.theater.repository;

import com.example.movieplatform.theater.entity.Screen;
import com.example.movieplatform.theater.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    boolean existsByScreen(Screen screen);
    long countByScreen(Screen screen);
}
