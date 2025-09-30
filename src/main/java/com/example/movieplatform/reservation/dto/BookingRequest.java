package com.example.movieplatform.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BookingRequest {
    private Long screeningInfoId;
    private List<TicketRequest> tickets;
    private List<Long> selectedSeatIds;
}
