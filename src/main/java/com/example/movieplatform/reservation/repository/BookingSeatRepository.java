package com.example.movieplatform.reservation.repository;

import com.example.movieplatform.reservation.entity.BookingSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingSeatRepository extends JpaRepository<BookingSeat, Long> {
    // 좌석 점유 현황
    @Query("""
         SELECT s.id
         FROM BookingSeat bs
         JOIN bs.seat s
         JOIN bs.booking b
         WHERE b.screeningInfo.id = :screeningInfoId
         AND b.bookingStatus IN (com.example.movieplatform.reservation.entity.Booking.BookingStatus.HOLD,
                  com.example.movieplatform.reservation.entity.Booking.BookingStatus.CONFIRMED
                  )
         """)
    List<Long> findOccupiedSeatByScreeningInfoId(@Param("screeningInfoId") Long screeningInfoId);
}
