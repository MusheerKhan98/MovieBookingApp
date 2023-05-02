package com.moviebookingapp.controller;

import com.moviebookingapp.custom.exception.MovieAlreadyExistsException;
import com.moviebookingapp.custom.exception.MovieNotFoundException;
import com.moviebookingapp.dto.BookingRequest;
import com.moviebookingapp.dto.MovieRequest;
import com.moviebookingapp.dto.MovieResponse;
import com.moviebookingapp.dto.TicketResponse;
import com.moviebookingapp.model.Movie;
import com.moviebookingapp.model.MoviePK;
import com.moviebookingapp.model.Ticket;
import com.moviebookingapp.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1.0/moviebooking")
public class MovieController {
    Logger log = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;


    /**
     * Fetches a list of all Movies in the Database.
     *
     * @return List<Movie>
     *
     */
    @GetMapping("/all")
    public ResponseEntity<List<Movie>> viewAllMovies(){
        log.info("-------Movie List Fetched---------");
        return ResponseEntity.ok(movieService.findAllMovies());
    }

    /**
     * Returns the record from the database using identifier - movieId
     *
     * @param movieId
     * @return Movie
     * @throws MovieNotFoundException
     * @throws AccessForbiddenException
     */

    @GetMapping("/viewMovie/{movieName}/{theatreName}")
    public ResponseEntity<Movie> viewMovie(@PathVariable String movieName,@PathVariable String theatreName)
            throws MovieNotFoundException {
        MoviePK moviePK = new MoviePK();
        moviePK.setMovieName(movieName);
        moviePK.setTheatreName(theatreName);
        return ResponseEntity.ok(movieService.viewMovie(moviePK));

    }

    /**
     * Adds a Movie object in the Database.
     * @param movie
     * @return Movie
     *
     */
    @PostMapping(value = "/addMovie", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Movie> addMovie(
            @RequestPart("movieRequest") MovieRequest movieRequest,
            @RequestPart("imageFile")MultipartFile file
            ) throws MovieAlreadyExistsException, IOException {
        log.info("entering Controller:viewAllMovies");
        Movie newMovie= movieService.addNewMovie(movieRequest,file);
        log.info("-------Movie Added Successfully---------");
        return new ResponseEntity<>(newMovie, HttpStatus.CREATED);
    }

    /**
     * Removes persisted Movie instance from the Database.
     *
     * @param movieName
     * @param theatreName
     * @return
     */
    @DeleteMapping("delete/{movieName}/{theatreName}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Boolean> deleteById(@PathVariable String movieName, @PathVariable String theatreName)
    {
        log.info("Entering MovieController:deleteById");
        MoviePK moviePK= new MoviePK();
        moviePK.setMovieName(movieName);
        moviePK.setTheatreName(theatreName);

        return  ResponseEntity.ok(movieService.deleteById(moviePK));
    }

    /**
     * Add new  Movie booking details to the Database.
     *
     * @param bookingRequest
     * @return
     */
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('customer')")
    public ResponseEntity<TicketResponse> bookTicket(@RequestBody BookingRequest bookingRequest) throws MovieNotFoundException {
        log.info("entering MovieController:bookTicket");
       return ResponseEntity.ok(movieService.bookTicket(bookingRequest));
    }

    /**
     * Fetches movies by name from database, name can be full or partial
     *
     * @param name
     * @return List<Movie>
     */
    @GetMapping("search/{name}")
    public ResponseEntity<List<Movie>> searchByName(@PathVariable(value = "name") String name){
        log.info("Entering MovieController:searchByName");
        return ResponseEntity.ok(movieService.getAllMoviesByName(name));
    }

    @PutMapping("/update/{count}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Boolean> updateTicketStatus(@PathVariable int count,@RequestBody MoviePK moviePK){
        log.info("Entering MovieController:updateTicketStatus");
        return ResponseEntity.ok(movieService.updateTicketStatus(count,moviePK));
    }

    @GetMapping("{movieName}/{theatreName}/viewAvailableSeats")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('customer')")
    public ResponseEntity<Integer> getNoOfAvailableSeats(@PathVariable String movieName,@PathVariable String theatreName) throws MovieNotFoundException {
        MoviePK moviePK = new MoviePK();
        moviePK.setTheatreName(theatreName);
        moviePK.setMovieName(movieName);
        return ResponseEntity.ok(movieService.getAvailableNoOfSeats(moviePK));
    }

    @PostMapping("/viewTicket")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('customer')")
    public ResponseEntity<Ticket> viewTicket(@RequestBody String ticketId) throws MovieNotFoundException {
        log.info("entering MovieController:viewTicket");
        return ResponseEntity.ok(movieService.viewTicket(ticketId));
    }

}
