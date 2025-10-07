package com.example.movieplatform.reservation.repository;

import com.example.movieplatform.reservation.entity.Booking;
import com.example.movieplatform.user.dto.UserReservationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("""
       SELECT new com.example.movieplatform.user.dto.UserReservationDto(
       b.id,
       b.totalPrice,
       m.title,
       m.posterUrl,
       t.theaterName,
       si.screeningDate,
       si.startTime,
       s.screenName,
       null,
       null,
       0,
       0L
       )
       FROM Booking b
       JOIN b.screeningInfo si
       JOIN si.movie m
       JOIN si.screen s
       JOIN s.theater t
       WHERE b.user.id = :userId
       AND b.bookingStatus = com.example.movieplatform.reservation.entity.Booking.BookingStatus.CONFIRMED
       ORDER BY b.bookingDate DESC, b.id DESC
""")
    List<UserReservationDto> findReservationByUserId(@Param("userId") Long userId);

    @Query("""
       SELECT new com.example.movieplatform.user.dto.UserReservationDto(
       b.id,
       b.totalPrice,
       m.title,
       m.posterUrl,
       t.theaterName,
       si.screeningDate,
       si.startTime,
       s.screenName,
       null,
       null,
       0,
       0L
       )
       FROM Booking b
       JOIN b.screeningInfo si
       JOIN si.movie m
       JOIN si.screen s
       JOIN s.theater t
       WHERE b.id = :bookingId
""")
    UserReservationDto findReservationByBookingId(@Param("bookingId") Long bookingId);


    // 5분이상 결제 안한 예약 찾기
    @Query("SELECT b FROM Booking b WHERE b.bookingStatus = 'HOLD' AND b.createdAt < :expireTime")
    List<Booking> findExpiredBookings(@Param("expireTime") LocalDateTime expireTime);
}
