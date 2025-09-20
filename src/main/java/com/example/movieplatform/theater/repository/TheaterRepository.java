package com.example.movieplatform.theater.repository;

import com.example.movieplatform.theater.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
    Boolean existsByTheaterName(String name);
}
