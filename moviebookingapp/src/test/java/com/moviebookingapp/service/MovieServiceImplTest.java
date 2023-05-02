package com.moviebookingapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.moviebookingapp.custom.exception.MovieAlreadyExistsException;
import com.moviebookingapp.custom.exception.MovieNotFoundException;
import com.moviebookingapp.dto.BookingRequest;
import com.moviebookingapp.kafka.Producer;
import com.moviebookingapp.model.Movie;
import com.moviebookingapp.model.MoviePK;
import com.moviebookingapp.model.Ticket;
import com.moviebookingapp.repository.MovieRepository;
import com.moviebookingapp.repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MovieServiceImpl.class})
@ExtendWith(SpringExtension.class)
class MovieServiceImplTest {
    @MockBean
    private MongoTemplate mongoTemplate;

    @MockBean
    private MovieRepository movieRepository;

    @Autowired
    private MovieServiceImpl movieServiceImpl;

    @MockBean
    private TicketRepository ticketRepository;

    @MockBean
    private Producer producer;

    /**
     * Method under test: {@link MovieServiceImpl#findAllMovies()}
     */
    @Test
    void testFindAllMovies() {
        ArrayList<Movie> movieList = new ArrayList<>();
        when(movieRepository.findAll()).thenReturn(movieList);
        List<Movie> actualFindAllMoviesResult = movieServiceImpl.findAllMovies();
        assertSame(movieList, actualFindAllMoviesResult);
        assertTrue(actualFindAllMoviesResult.isEmpty());
        verify(movieRepository).findAll();
    }


    /**
     * Method under test: {@link MovieServiceImpl#deleteById(MoviePK)}
     */
    @Test
    void testDeleteById() {
        Movie movie2 = new Movie();
        movie2.setMoviePK(new MoviePK("Movie Name", "Theatre Name"));
        movie2.setNoOfTickets(1);

        movie2.setTicketStatus(true);

        when(movieRepository.findById(Mockito.<MoviePK>any())).thenReturn(Optional.of(movie2));
        doNothing().when(movieRepository).deleteById(Mockito.<MoviePK>any());
        MoviePK id = new MoviePK("Movie Name", "Theatre Name");

        movieServiceImpl.deleteById(id);
        verify(movieRepository).deleteById(Mockito.<MoviePK>any());
        assertEquals("Movie Name", id.getMovieName());
        assertEquals("Theatre Name", id.getTheatreName());
        assertTrue(movieServiceImpl.findAllMovies().isEmpty());
    }

    /**
     * Method under test: {@link MovieServiceImpl#bookTicket(BookingRequest)}
     */
    @Test
    void testBookTicket() throws MovieNotFoundException {
        Movie movie = new Movie();
        movie.setMoviePK(new MoviePK("Movie Name", "Theatre Name"));
        movie.setNoOfTickets(1);
        movie.setTicketStatus(true);
        Optional<Movie> ofResult = Optional.of(movie);

        Movie movie2 = new Movie();
        movie2.setMoviePK(new MoviePK("Movie Name", "Theatre Name"));
        movie2.setNoOfTickets(1);
        movie2.setTicketStatus(true);
        when(movieRepository.save(Mockito.<Movie>any())).thenReturn(movie2);
        when(movieRepository.findById(Mockito.<MoviePK>any())).thenReturn(ofResult);

        Movie movie3 = new Movie();
        movie3.setMoviePK(new MoviePK("Movie Name", "Theatre Name"));
        movie3.setNoOfTickets(1);
        
        movie3.setTicketStatus(true);

        Ticket ticket = new Ticket();
        ticket.setMovie(movie3);
        ticket.setNoOfSeats(1);
        ticket.setTheatre("Theatre");
        ticket.setUsername("janedoe");
        when(ticketRepository.save(Mockito.<Ticket>any())).thenReturn(ticket);
        assertEquals("1 booked for Movie Name at Theatre Name",
                movieServiceImpl.bookTicket(new BookingRequest("Movie Name", 1, "Theatre Name", "janedoe")));
        verify(movieRepository).save(Mockito.<Movie>any());
        verify(movieRepository).findById(Mockito.<MoviePK>any());
        verify(ticketRepository).save(Mockito.<Ticket>any());
    }

    /**
     * Method under test: {@link MovieServiceImpl#bookTicket(BookingRequest)}
     */
    @Test
    void testBookTicket4() throws MovieNotFoundException {
        Movie movie = mock(Movie.class);
        when(movie.getMoviePK()).thenReturn(new MoviePK("Movie Name", "Theatre Name"));
        when(movie.isTicketStatus()).thenReturn(false);
        when(movie.getNoOfTickets()).thenReturn(1);
        doNothing().when(movie).setMoviePK(Mockito.<MoviePK>any());
        doNothing().when(movie).setNoOfTickets(anyInt());
        
        doNothing().when(movie).setTicketStatus(anyBoolean());
        movie.setMoviePK(new MoviePK("Movie Name", "Theatre Name"));
        movie.setNoOfTickets(1);
        
        movie.setTicketStatus(true);
        Optional<Movie> ofResult = Optional.of(movie);

        Movie movie2 = new Movie();
        movie2.setMoviePK(new MoviePK("Movie Name", "Theatre Name"));
        movie2.setNoOfTickets(1);
        
        movie2.setTicketStatus(true);
        when(movieRepository.save(Mockito.<Movie>any())).thenReturn(movie2);
        when(movieRepository.findById(Mockito.<MoviePK>any())).thenReturn(ofResult);

        Movie movie3 = new Movie();
        movie3.setMoviePK(new MoviePK("Movie Name", "Theatre Name"));
        movie3.setNoOfTickets(1);
        
        movie3.setTicketStatus(true);

        Ticket ticket = new Ticket();
        ticket.setMovie(movie3);
        ticket.setNoOfSeats(1);
        ticket.setTheatre("Theatre");
        ticket.setUsername("janedoe");
        when(ticketRepository.save(Mockito.<Ticket>any())).thenReturn(ticket);
        assertEquals("SOLD OUT",
                movieServiceImpl.bookTicket(new BookingRequest("Movie Name", 1, "Theatre Name", "janedoe")));
        verify(movieRepository).findById(Mockito.<MoviePK>any());
        verify(movie).isTicketStatus();
        verify(movie).getNoOfTickets();
        verify(movie).setMoviePK(Mockito.<MoviePK>any());
        verify(movie).setNoOfTickets(anyInt());
       
        verify(movie).setTicketStatus(anyBoolean());
    }

    /**
     * Method under test: {@link MovieServiceImpl#bookTicket(BookingRequest)}
     */
    @Test
    void testBookTicket5() throws MovieNotFoundException {
        Movie movie = mock(Movie.class);
        when(movie.getMoviePK()).thenReturn(new MoviePK("Movie Name", "Theatre Name"));
        when(movie.isTicketStatus()).thenReturn(true);
        when(movie.getNoOfTickets()).thenReturn(0);
        doNothing().when(movie).setMoviePK(Mockito.<MoviePK>any());
        doNothing().when(movie).setNoOfTickets(anyInt());
        
        doNothing().when(movie).setTicketStatus(anyBoolean());
        movie.setMoviePK(new MoviePK("Movie Name", "Theatre Name"));
        movie.setNoOfTickets(1);
        
        movie.setTicketStatus(true);
        Optional<Movie> ofResult = Optional.of(movie);

        Movie movie2 = new Movie();
        movie2.setMoviePK(new MoviePK("Movie Name", "Theatre Name"));
        movie2.setNoOfTickets(1);
        
        movie2.setTicketStatus(true);
        when(movieRepository.save(Mockito.<Movie>any())).thenReturn(movie2);
        when(movieRepository.findById(Mockito.<MoviePK>any())).thenReturn(ofResult);

        Movie movie3 = new Movie();
        movie3.setMoviePK(new MoviePK("Movie Name", "Theatre Name"));
        movie3.setNoOfTickets(1);
        
        movie3.setTicketStatus(true);

        Ticket ticket = new Ticket();
        ticket.setMovie(movie3);
        ticket.setNoOfSeats(1);
        ticket.setTheatre("Theatre");

        ticket.setUsername("janedoe");
        when(ticketRepository.save(Mockito.<Ticket>any())).thenReturn(ticket);
        assertEquals("Only 0 remaining",
                movieServiceImpl.bookTicket(new BookingRequest("Movie Name", 1, "Theatre Name", "janedoe")));
        verify(movieRepository).findById(Mockito.<MoviePK>any());
        verify(movie).isTicketStatus();
        verify(movie, atLeast(1)).getNoOfTickets();
        verify(movie).setMoviePK(Mockito.<MoviePK>any());
        verify(movie).setNoOfTickets(anyInt());
       
        verify(movie).setTicketStatus(anyBoolean());
    }

    /**
     * Method under test: {@link MovieServiceImpl#bookTicket(BookingRequest)}
     */
    @Test
    void testBookTicket6() throws MovieNotFoundException {
        Movie movie = new Movie();
        movie.setMoviePK(new MoviePK("Movie Name", "Theatre Name"));
        movie.setNoOfTickets(1);
        
        movie.setTicketStatus(true);
        when(movieRepository.save(Mockito.<Movie>any())).thenReturn(movie);
        when(movieRepository.findById(Mockito.<MoviePK>any())).thenReturn(Optional.empty());
        Movie movie2 = mock(Movie.class);
        when(movie2.getMoviePK()).thenReturn(new MoviePK("Movie Name", "Theatre Name"));
        when(movie2.isTicketStatus()).thenReturn(true);
        when(movie2.getNoOfTickets()).thenReturn(1);
        doNothing().when(movie2).setMoviePK(Mockito.<MoviePK>any());
        doNothing().when(movie2).setNoOfTickets(anyInt());

        doNothing().when(movie2).setTicketStatus(anyBoolean());
        movie2.setMoviePK(new MoviePK("Movie Name", "Theatre Name"));
        movie2.setNoOfTickets(1);
        
        movie2.setTicketStatus(true);

        Movie movie3 = new Movie();
        movie3.setMoviePK(new MoviePK("Movie Name", "Theatre Name"));
        movie3.setNoOfTickets(1);
        
        movie3.setTicketStatus(true);

        Ticket ticket = new Ticket();
        ticket.setMovie(movie3);
        ticket.setNoOfSeats(1);
        ticket.setTheatre("Theatre");

        ticket.setUsername("janedoe");
        when(ticketRepository.save(Mockito.<Ticket>any())).thenReturn(ticket);
        assertThrows(MovieNotFoundException.class,
                () -> movieServiceImpl.bookTicket(new BookingRequest("Movie Name", 1, "Theatre Name", "janedoe")));
        verify(movieRepository).findById(Mockito.<MoviePK>any());
        verify(movie2).setMoviePK(Mockito.<MoviePK>any());
        verify(movie2).setNoOfTickets(anyInt());

        verify(movie2).setTicketStatus(anyBoolean());
    }

    /**
     * Method under test: {@link MovieServiceImpl#getAllMoviesByName(String)}
     */
    @Test
    void testGetAllMoviesByName() {
        ArrayList<Movie> movieList = new ArrayList<>();
        when(mongoTemplate.find(Mockito.<Query>any(), Mockito.<Class<Movie>>any())).thenReturn(movieList);
        List<Movie> actualAllMoviesByName = movieServiceImpl.getAllMoviesByName("Name");
        assertSame(movieList, actualAllMoviesByName);
        assertTrue(actualAllMoviesByName.isEmpty());
        verify(mongoTemplate).find(Mockito.<Query>any(), Mockito.<Class<Movie>>any());
    }

    /**
     * Method under test: {@link MovieServiceImpl#updateTicketStatus(int, MoviePK)}
     */
    @Test
    void testUpdateTicketStatus() {
        Movie movie = new Movie();
        movie.setMoviePK(new MoviePK("Movie Name", "Theatre Name"));
        movie.setNoOfTickets(1);
        
        movie.setTicketStatus(true);
        Optional<Movie> ofResult = Optional.of(movie);

        Movie movie2 = new Movie();
        movie2.setMoviePK(new MoviePK("Movie Name", "Theatre Name"));
        movie2.setNoOfTickets(1);
        
        movie2.setTicketStatus(true);
        when(movieRepository.save(Mockito.<Movie>any())).thenReturn(movie2);
        when(movieRepository.findById(Mockito.<MoviePK>any())).thenReturn(ofResult);
        assertTrue(movieServiceImpl.updateTicketStatus(3, new MoviePK("Movie Name", "Theatre Name")));
        verify(movieRepository).save(Mockito.<Movie>any());
        verify(movieRepository).findById(Mockito.<MoviePK>any());
    }

    /**
     * Method under test: {@link MovieServiceImpl#updateTicketStatus(int, MoviePK)}
     */
    @Test
    void testUpdateTicketStatus2() {
        Movie movie = new Movie();
        movie.setMoviePK(new MoviePK("Movie Name", "Theatre Name"));
        movie.setNoOfTickets(1);
        
        movie.setTicketStatus(true);
        when(movieRepository.save(Mockito.<Movie>any())).thenReturn(movie);
        when(movieRepository.findById(Mockito.<MoviePK>any())).thenReturn(Optional.empty());
        assertFalse(movieServiceImpl.updateTicketStatus(3, new MoviePK("Movie Name", "Theatre Name")));
        verify(movieRepository).findById(Mockito.<MoviePK>any());
    }
}

