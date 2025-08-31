package com.example.movieplatform.point.repository;

import com.example.movieplatform.point.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {
    public List<Point> findByUserId(Long userId);
}
