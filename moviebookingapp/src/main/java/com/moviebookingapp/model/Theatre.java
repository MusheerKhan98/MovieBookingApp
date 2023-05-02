package com.moviebookingapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Theatre {

    @Id
    private String theatreId;
    private String theatreName;
    private String theatreCity;
    private List<Movie> movies;

}
