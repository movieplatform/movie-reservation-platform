package com.example.movieplatform.screen.entity;

import com.example.movieplatform.screen.repository.ScreenRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String screenName;
    // 1관 ~ 5관

    public Screen() {}

    public Screen(String screenName) {
        this.screenName = screenName;
    }
}
