package com.example.movieplatform.reservation.service;

import com.example.movieplatform.common.exception.EntityNotFoundException;
import com.example.movieplatform.point.repository.PointRepository;
import com.example.movieplatform.reservation.entity.Booking;
import com.example.movieplatform.reservation.entity.BookingTicket;
import com.example.movieplatform.reservation.repository.BookingRepository;
import com.example.movieplatform.reservation.repository.BookingSeatRepository;
import com.example.movieplatform.reservation.repository.BookingTicketRepository;
import com.example.movieplatform.user.dto.UserReservationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final BookingRepository bookingRepository;
    private final BookingSeatRepository bookingSeatRepository;
    private final BookingTicketRepository bookingTicketRepository;
    private final PointRepository pointRepository;

    public UserReservationDto getPaymentReservation(Long bookingId) {
        UserReservationDto myReservation = bookingRepository.findReservationByBookingId(bookingId);
        if (myReservation == null) {
            throw new EntityNotFoundException(bookingId.toString());
        }
        // 2. 좌석 요약
        List<String> seats = bookingSeatRepository.findSeatNumbersByBookingId(bookingId);
        myReservation.setSeatSummary(String.join(", ", seats));

        // 3. 티켓 요약
        List<BookingTicket> tickets = bookingTicketRepository.findByBookingId(bookingId);
        myReservation.setTicketCount(tickets.size());

        String ticketSummary = tickets.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getCustomerType().getDisplayName(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .map(e -> e.getKey() + " " + e.getValue() + "명")
                .collect(Collectors.joining(", "));
        myReservation.setTicketSummary(ticketSummary);

        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new EntityNotFoundException(bookingId.toString()));
        Long userId = booking.getUser().getId();
        if (userId != null) {
            Long pointSum = pointRepository.getUserPointBalance(userId);
            myReservation.setUserPoint(pointSum);
        }

        return myReservation;
    }

    public List<UserReservationDto> getUserReservation(Long userId) {
        List<UserReservationDto> myReservations = bookingRepository.findReservationByUserId(userId);

        for(UserReservationDto myReservation : myReservations){
            // 좌석 요약
            List<String> seats = bookingSeatRepository.findSeatNumbersByBookingId(myReservation.getBookingId());
            myReservation.setSeatSummary(String.join(", ", seats));

            // 인원 요약
            List<BookingTicket> tickets = bookingTicketRepository.findByBookingId(myReservation.getBookingId());

            int ticketCount = tickets.size();
            myReservation.setTicketCount(ticketCount);

            String ticketSummary = tickets.stream()
                    .collect(Collectors.groupingBy(
                            t -> t.getCustomerType().getDisplayName(),
                            Collectors.counting()
                    ))
                    .entrySet().stream()
                    .map(e -> e.getKey() + " " + e.getValue() + "명")
                    .collect(Collectors.joining(", "));

            myReservation.setTicketSummary(ticketSummary);
        }
        return myReservations;
    }
}
