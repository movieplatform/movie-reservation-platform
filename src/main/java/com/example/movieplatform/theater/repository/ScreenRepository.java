package com.example.movieplatform.theater.repository;

import com.example.movieplatform.theater.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScreenRepository extends JpaRepository<Screen, Long> {
    @Query("SELECT s FROM Screen s WHERE s.theater.id = :theaterId")
    List<Screen> findByTheaterId(@Param("theaterId") Long theaterId);
    @Query("SELECT s.id FROM Screen s")
    List<Long> findAllIds();

}
