package com.example.movieplatform.entity;

import com.example.movieplatform.reservation.entity.Booking;
import jakarta.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    private Long usedPoint;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    public enum PaymentMethod{
        ORIGINAL,
        TOSS
    }
}
