package com.moviebookingapp.service;

import com.moviebookingapp.custom.exception.MovieAlreadyExistsException;
import com.moviebookingapp.custom.exception.MovieNotFoundException;
import com.moviebookingapp.dto.BookingRequest;
import com.moviebookingapp.dto.MovieRequest;
import com.moviebookingapp.dto.MovieResponse;
import com.moviebookingapp.dto.TicketResponse;
import com.moviebookingapp.model.Movie;
import com.moviebookingapp.model.MoviePK;
import com.moviebookingapp.model.Ticket;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface MovieService {

    List<Movie> findAllMovies();

    Movie addNewMovie(MovieRequest movieRequest, MultipartFile file) throws MovieAlreadyExistsException, IOException;

    boolean deleteById(MoviePK id);

    TicketResponse bookTicket(BookingRequest bookingRequest) throws MovieNotFoundException;

    List<Movie> getAllMoviesByName(String name);

    boolean updateTicketStatus(int count,MoviePK moviePK);

    Movie viewMovie(MoviePK moviePK) throws MovieNotFoundException;

    int getAvailableNoOfSeats(MoviePK moviePK) throws MovieNotFoundException;

    Ticket viewTicket(String ticketId);
}
