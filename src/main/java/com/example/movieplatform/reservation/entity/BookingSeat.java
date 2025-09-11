package com.example.movieplatform.reservation.entity;

import com.example.movieplatform.screen.entity.Seat;
import jakarta.persistence.*;

@Entity
@Table(name = "booking_seat")
public class BookingSeat {  // 예약 가능 여부 확인 가능
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id")
    private Seat seat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
