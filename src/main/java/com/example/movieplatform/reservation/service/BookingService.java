package com.example.movieplatform.reservation.service;

import com.example.movieplatform.reservation.dto.BookingRequest;
import com.example.movieplatform.reservation.dto.TicketRequest;
import com.example.movieplatform.reservation.entity.Booking;
import com.example.movieplatform.reservation.entity.BookingSeat;
import com.example.movieplatform.reservation.entity.BookingTicket;
import com.example.movieplatform.reservation.entity.ScreeningInfo;
import com.example.movieplatform.reservation.repository.BookingRepository;
import com.example.movieplatform.reservation.repository.BookingSeatRepository;
import com.example.movieplatform.reservation.repository.BookingTicketRepository;
import com.example.movieplatform.reservation.repository.ScreeningInfoRepository;
import com.example.movieplatform.theater.entity.Seat;
import com.example.movieplatform.theater.repository.SeatRepository;
import com.example.movieplatform.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookingTicketRepository bookingTicketRepository;
    private final BookingSeatRepository bookingSeatRepository;
    private final ScreeningInfoRepository screeningInfoRepository;
    private final SeatRepository seatRepository;

    public void saveBookingInfo(User user, BookingRequest bookingRequest) {
        ScreeningInfo screeningInfo = screeningInfoRepository.findById(bookingRequest.getScreeningInfoId())
                .orElseThrow(() -> new RuntimeException("상영정보 없음"));

        Booking booking = Booking.ofBooking(user, screeningInfo);
        bookingRepository.save(booking);

        List<BookingTicket> tickets = new ArrayList<>();

        for(TicketRequest tr : bookingRequest.getTickets()) {
            if(tr.getCount() <= 0 ) continue; // 에를들어 청소년 0명일때 청소년 티켓은 생성 X

            BookingTicket.CustomerType type =
                    BookingTicket.CustomerType.fromDisplayName(tr.getCustomerType());

            for(int i = 0; i < tr.getCount(); i++){
                BookingTicket ticket  = new BookingTicket(booking, type);
                tickets.add(ticket);
            }
        }
        bookingTicketRepository.saveAll(tickets);

        List<Seat> seats = seatRepository.findAllById(bookingRequest.getSelectedSeatIds());
        List<BookingSeat> bookingSeats = seats.stream()
                .map(seat -> {
                    return new BookingSeat(seat, booking);
                }).toList();
        bookingSeatRepository.saveAll(bookingSeats);

        Long totalPrice = tickets.stream().mapToLong(BookingTicket::getPrice).sum();
        booking.updateTotalPrice(totalPrice);  // 예약자가 결제할 총금액(예를들면 어른2 어린이2명이면 총 4명가격)
        bookingRepository.save(booking);
    }
}
