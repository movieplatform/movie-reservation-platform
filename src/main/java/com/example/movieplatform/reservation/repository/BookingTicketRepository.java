package com.example.movieplatform.reservation.repository;

import com.example.movieplatform.reservation.entity.BookingTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingTicketRepository extends JpaRepository<BookingTicket, Long> {
    List<BookingTicket> findByBookingId(Long bookingId);
}
