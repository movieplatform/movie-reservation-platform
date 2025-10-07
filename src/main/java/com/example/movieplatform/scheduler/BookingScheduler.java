package com.example.movieplatform.scheduler;

import com.example.movieplatform.reservation.entity.Booking;
import com.example.movieplatform.reservation.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingScheduler {
    private final BookingRepository bookingRepository;

    @Scheduled(fixedRate = 60000)
    public void releaseExpiredReservations() {
        LocalDateTime expireTime = LocalDateTime.now().minusMinutes(5);
        List<Booking> expiredBookings = bookingRepository.findExpiredBookings(expireTime);

        expiredBookings.forEach(booking -> {
            booking.updateBookingStatus(Booking.BookingStatus.CANCELLED);
            bookingRepository.save(booking);
        });
    }
}
