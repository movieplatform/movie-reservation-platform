package com.example.movieplatform.payment.service;

import com.example.movieplatform.common.exception.EntityNotFoundException;
import com.example.movieplatform.payment.entity.Payment;
import com.example.movieplatform.payment.repository.PaymentRepository;
import com.example.movieplatform.point.entity.Point;
import com.example.movieplatform.point.repository.PointRepository;
import com.example.movieplatform.reservation.entity.Booking;
import com.example.movieplatform.reservation.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final PointRepository pointRepository;

    public void savePayment(Long usedPoint, Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow( () -> new EntityNotFoundException("Booking not found"));
        Payment payment = new Payment(usedPoint, booking);
        paymentRepository.save(payment);

        if (usedPoint > 0){
            Point point = Point.useForPayment(booking.getUser(),usedPoint);
            pointRepository.save(point);  // 포인트 사용한만큼 차감
        }

        long paymentAmount = booking.getTotalPrice() - usedPoint;
        long earnedPoints = Math.round(paymentAmount * 0.05);

        if (earnedPoints > 0){
            Point point = Point.earnForPurchase(booking.getUser(),earnedPoints);
            pointRepository.save(point);
        }

        // 결제 완료로 변경
        booking.updateBookingStatus(Booking.BookingStatus.CONFIRMED);
        bookingRepository.save(booking);

    }
}
