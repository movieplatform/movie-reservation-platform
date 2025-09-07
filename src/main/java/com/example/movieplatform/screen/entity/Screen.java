package com.example.movieplatform.screen.entity;

import com.example.movieplatform.screen.repository.ScreenRepository;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String screenName;

    @Column(name = "seat_rows",  nullable = false)
    private int rows;
    @Column(name = "seat_cols",   nullable = false)
    private int cols;
    // 1관 ~ 5관

    public Screen() {}

    public Screen(String screenName) {
        this.screenName = screenName;
    }

    public void updateSeatSize(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }
}
