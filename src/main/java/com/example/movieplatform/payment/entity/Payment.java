package com.example.movieplatform.payment.entity;

import com.example.movieplatform.reservation.entity.Booking;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Getter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long amount; // 결제금액 ( 총 결제금액 - 사용포인트)
    private Long usedPoint;  // 사용한 포인트
    private LocalDateTime paidAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    public Payment(){}

    public Payment(Long usedPoint, Booking booking) {
        this.amount = booking.getTotalPrice() - usedPoint;
        this.usedPoint = usedPoint;
        this.paidAt = LocalDateTime.now();
        this.booking = booking;
    }

}
