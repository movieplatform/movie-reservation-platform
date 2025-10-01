package com.example.movieplatform.reservation.entity;

import com.example.movieplatform.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Table(name = "booking")
@Getter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate bookingDate;
    private Long totalPrice;
    private BookingStatus bookingStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screeingInfo_id")
    private ScreeningInfo screeningInfo;

    public enum BookingStatus {
        HOLD,    // 임시 점유
        CONFIRMED,  // 결제 완료
        CANCELLED   // 결제실패, 시간초과, 사용자 취소
    }

    public Booking(){}

    public Booking(User user, ScreeningInfo screeningInfo){
        this.bookingDate = LocalDate.now();
        this.totalPrice = 0L;
        this.bookingStatus = BookingStatus.HOLD;
        this.user = user;
        this.screeningInfo = screeningInfo;
    }

    // 결제 버튼 누를시 예약 정보 저장
    public static Booking ofBooking(User user, ScreeningInfo screeningInfo){
        return new Booking(user, screeningInfo);
    }

    public void updateTotalPrice(Long totalPrice){
        this.totalPrice = totalPrice;
    }

    public void updateBookingStatus(BookingStatus bookingStatus){
        this.bookingStatus = bookingStatus;
    }
}
