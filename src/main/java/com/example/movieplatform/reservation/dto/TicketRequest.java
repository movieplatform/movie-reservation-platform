package com.example.movieplatform.reservation.dto;

import com.example.movieplatform.reservation.entity.BookingTicket;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TicketRequest {
    private String customerType;
    private int count;
}
