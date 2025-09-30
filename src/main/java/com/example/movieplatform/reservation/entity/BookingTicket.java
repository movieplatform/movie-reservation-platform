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
    public enum CustomerType {
        ADULT(0L, "성인"),
        TEEN(-1000L, "청소년"),
        SENIOR(-2000L, "노약자"),
        DISABLED(-3000L, "장애인");

        private final Long discount;
        private final String displayName;

        CustomerType(long discount, String displayName) {
            this.discount = discount;
            this.displayName = displayName;
        }

        public static CustomerType fromDisplayName(String displayName) {
            for (CustomerType type : values()) {
                if (type.displayName.equals(displayName)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Unknown customer type: " + displayName);
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
