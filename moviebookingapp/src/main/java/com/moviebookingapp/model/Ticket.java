package com.moviebookingapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
@Getter
@Setter
public class Ticket {
    @Id
    private String ticketId;
    private int noOfSeats;
    private Movie movie;
    private String theatre;
    private List<Integer> seatNumber;
    private String username;
}
