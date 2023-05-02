package com.moviebookingapp.dto;



import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

@Getter
@Setter
public class MovieRequest {

    private String movieName;
    private String theatreName;
    private int noOfTickets;
    private String movieGenre;
    private String movieHours;
    private String movieLanguage;
    private String movieDescription;
    private String movieRating;
    private LocalDate movieDate;
}
