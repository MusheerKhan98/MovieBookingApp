package com.moviebookingapp.repository;

import com.moviebookingapp.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface TicketRepository extends MongoRepository<Ticket, String> {
}
