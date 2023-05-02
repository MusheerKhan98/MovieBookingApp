package com.moviebookingapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class TicketResponse {

    private String movieName;
    private String theatreName;
    private List<Integer> seatNumber;
    private boolean canBook;
    private String ticketId;
}
