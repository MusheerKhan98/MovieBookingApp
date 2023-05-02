package com.moviebookingapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MovieResponse {

    private String movieName;
    private String theatreName;
    private String movieGenre;
    private String movieHours;
    private String movieLanguage;
    private String movieDescription;
    private String movieRating;
}
