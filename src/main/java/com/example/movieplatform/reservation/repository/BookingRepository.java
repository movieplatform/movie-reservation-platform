package com.example.movieplatform.reservation.repository;

import com.example.movieplatform.reservation.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
