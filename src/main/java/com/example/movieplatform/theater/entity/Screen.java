package com.example.movieplatform.theater.entity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;

    public Screen() {}

    public Screen(Theater theater, String screenName) {
        this.theater = theater;
        this.screenName = screenName;
    }

    public void updateSeatSize(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }
}
