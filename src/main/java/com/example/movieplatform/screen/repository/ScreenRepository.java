package com.example.movieplatform.screen.repository;

import com.example.movieplatform.screen.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreenRepository extends JpaRepository<Screen, Long> {
    boolean existsByScreenName(String screenName);
}
