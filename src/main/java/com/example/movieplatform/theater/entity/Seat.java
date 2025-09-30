package com.example.movieplatform.theater.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;
    @Column(name = "row_num", nullable = false)
    private int rowNumber;
    @Column(name = "col_num", nullable = false)
    private int colNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id")
    private Screen screen;

    public Seat() {}

    public Seat(String seatNumber, int rowNumber, int colNumber, Screen screen) {
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.screen = screen;
    }

}
