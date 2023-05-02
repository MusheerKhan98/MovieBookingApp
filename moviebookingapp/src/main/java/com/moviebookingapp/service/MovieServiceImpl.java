package com.moviebookingapp.service;

import com.moviebookingapp.controller.MovieController;
import com.moviebookingapp.custom.exception.MovieAlreadyExistsException;
import com.moviebookingapp.custom.exception.MovieNotFoundException;
import com.moviebookingapp.dto.BookingRequest;
import com.moviebookingapp.dto.MovieRequest;
import com.moviebookingapp.dto.MovieResponse;
import com.moviebookingapp.dto.TicketResponse;
import com.moviebookingapp.kafka.Producer;
import com.moviebookingapp.model.Movie;
import com.moviebookingapp.model.MoviePK;
import com.moviebookingapp.model.Ticket;
import com.moviebookingapp.repository.MovieRepository;
import com.moviebookingapp.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieServiceImpl implements MovieService{

    Logger log = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Producer producer;

    public List<Movie> findAllMovies(){
        log.info("Entering MovieService:findAllMovies");
        return movieRepository.findAll();
    }

    @Override
    public Movie addNewMovie(MovieRequest movieRequest, MultipartFile file) throws MovieAlreadyExistsException, IOException {
        log.info("Entering MovieService:addNewMovie");
        MoviePK moviePK = new MoviePK();
        moviePK.setMovieName(movieRequest.getMovieName());
        moviePK.setTheatreName(movieRequest.getTheatreName());
        if(movieRepository.findById(moviePK).isPresent()){
            throw new MovieAlreadyExistsException("The movie you are trying to add already exists");
        }
        else {
            Movie movie = new Movie();
            movie.setMoviePK(moviePK);
            movie.setTicketStatus(true);
            movie.setNoOfTickets(movieRequest.getNoOfTickets());
            movie.setMovieDate(movieRequest.getMovieDate());
            movie.setMovieDescription(movieRequest.getMovieDescription());
            movie.setMovieGenre(movieRequest.getMovieGenre());
            movie.setMovieHours(movieRequest.getMovieHours());
            movie.setMovieLanguage(movieRequest.getMovieLanguage());
            movie.setMovieRating(movieRequest.getMovieRating());
            String id = imageService.addPhoto(file.getName(),file);
            movie.setMovieImage(imageService.getPhoto(id));
           return  movieRepository.save(movie);

        }
    }


    @Override
    public boolean deleteById(MoviePK id) {
        log.info("Entering MovieService:deleteById");

        if (movieRepository.findById(id).isPresent()) {

            movieRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public TicketResponse bookTicket(BookingRequest bookingRequest) throws MovieNotFoundException {
        log.info("Entering MovieService:bookTicket");
        List<Integer> seatNumbers= new ArrayList<>();
        TicketResponse response = new TicketResponse();
        MoviePK moviePK = new MoviePK();
        moviePK.setMovieName(bookingRequest.getMovieName());
        moviePK.setTheatreName(bookingRequest.getTheatreName());
        Optional<Movie> movieOptional = movieRepository.findById(moviePK);
        if(movieOptional.isPresent()){
            Movie movie= movieOptional.get();
            if(movie.isTicketStatus() && movie.getNoOfTickets()>=bookingRequest.getNoOfSeats()) {
                int totalTicketsLeft = movie.getNoOfTickets();
                movie.setNoOfTickets(movie.getNoOfTickets() - bookingRequest.getNoOfSeats());

                if(movie.getNoOfTickets()==0){
                    producer.publishToTopic("SOLD OUT");
                    movie.setTicketStatus(false);
                }
                    movieRepository.save(movie);
                    Ticket ticket = new Ticket();
                    ticket.setTicketId(UUID.randomUUID().toString());
                    ticket.setMovie(movie);
                    ticket.setNoOfSeats(bookingRequest.getNoOfSeats());
                    ticket.setTheatre(bookingRequest.getTheatreName());
                    ticket.setUsername(bookingRequest.getUsername());

                    response.setCanBook(true);
                    response.setMovieName(bookingRequest.getMovieName());
                    response.setTheatreName(bookingRequest.getTheatreName());
                    for(int i=0;i< bookingRequest.getNoOfSeats();i++){

                        seatNumbers.add(totalTicketsLeft-i);
                }
                    ticket.setSeatNumber(seatNumbers);
                    response.setSeatNumber(seatNumbers);
                    response.setTicketId(ticket.getTicketId().toString());
                    ticketRepository.save(ticket);
                    return response;
            }
            else{
               if(movie.getNoOfTickets()<bookingRequest.getNoOfSeats()) {
                   response.setCanBook(false);
                   return response;
               }
               else {
                   response.setCanBook(false);
                   return response;
               }
            }
        }
        else{
           throw new MovieNotFoundException("Movie not found with given ID");
        }

    }
    @Override
    public List<Movie> getAllMoviesByName(String name){
        log.info("Entering MovieService:getAllMoviesByName");
        Query query= new Query();
        query.addCriteria(Criteria.where("MoviePK.movieName").regex(name,"i"));
        List<Movie> movie =  mongoTemplate.find(query,Movie.class);
        return movie;
    }

    @Override
    public boolean updateTicketStatus(int count,MoviePK moviePK) {
        log.info("Entering MovieService:updateTicketStatus");
        Optional<Movie> movieOptional= movieRepository.findById(moviePK);
        if(movieOptional.isPresent()){
            Movie movie = movieOptional.get();
            movie.setNoOfTickets(movie.getNoOfTickets()+count);
            movie.setTicketStatus(true);
            movieRepository.save(movie);
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public Movie viewMovie(MoviePK moviePK) throws MovieNotFoundException {
        log.info("Entering MovieService:viewMovie");
        Optional<Movie> movieOptional= movieRepository.findById(moviePK);
        if(movieOptional.isPresent()){
            return  movieOptional.get();
        }
        else{
            throw new MovieNotFoundException("Movie not found with given ID");
        }
    }

    @Override
    public int getAvailableNoOfSeats(MoviePK moviePK) throws MovieNotFoundException {
        log.info("Entering MovieService:getAvailableNoOfSeats");
        Optional<Movie> movieOptional= movieRepository.findById(moviePK);
        if(movieOptional.isPresent()){
            return  movieOptional.get().getNoOfTickets();
        }
        else{
            throw new MovieNotFoundException("Movie not found with given ID");
        }
    }

    @Override
    public Ticket viewTicket(String ticketId) {
        return ticketRepository.findById(ticketId).get();
    }
}