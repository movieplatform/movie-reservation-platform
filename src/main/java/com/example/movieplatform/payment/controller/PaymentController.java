package com.example.movieplatform.payment.controller;

import com.example.movieplatform.payment.service.PaymentService;
import com.example.movieplatform.reservation.service.ReservationService;
import com.example.movieplatform.user.dto.UserReservationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final ReservationService reservationService;

    @GetMapping("/reservation")
    public ResponseEntity<UserReservationDto> getPaymentReservation(@RequestParam Long bookingId) {
        UserReservationDto response = reservationService.getPaymentReservation(bookingId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<String> savePayment(@RequestParam Long usedPoint, @RequestParam Long bookingId) {
        paymentService.savePayment(usedPoint, bookingId);
        return ResponseEntity.ok("결제 완료!!");
    }

    @GetMapping("/cancel")
    public ResponseEntity<String> cancelReservation(@RequestParam Long bookingId) {
        reservationService.cancelReservation(bookingId);
        return ResponseEntity.ok("예약 취소!!");
    }
}
