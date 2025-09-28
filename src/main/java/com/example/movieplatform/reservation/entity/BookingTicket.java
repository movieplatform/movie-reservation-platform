package com.example.movieplatform.reservation.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class BookingTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    private Long price;   // 시간별로 차등 + 고객 유형별로 차등계산

    @Getter
    public enum CustomerType{
        ADULT(0L),
        TEEN(-1000L),
        SENIOR(-2000L),
        DISABLED(-3000L);

        private final Long discount;

        CustomerType(long discount) {
            this.discount = discount;
        }
    }
    public BookingTicket(){}

    public BookingTicket(Booking booking, CustomerType customerType) {
        this.booking = booking;
        this.customerType = customerType;
        this.price = calculatePrice();
    }

    private Long calculatePrice(){
        Long basePrice = booking.getScreeningInfo().getPrice();
        return basePrice + customerType.getDiscount();
    }
}
