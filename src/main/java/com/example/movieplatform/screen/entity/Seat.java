package com.example.movieplatform.screen.entity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screen_id")
    private Screen screen;

    public Seat() {}

    public Seat(String seatNumber, Screen screen) {
        this.seatNumber = seatNumber;
        this.screen = screen;
    }

}
