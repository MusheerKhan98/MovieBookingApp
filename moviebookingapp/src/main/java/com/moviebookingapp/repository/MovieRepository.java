package com.moviebookingapp.repository;

import com.moviebookingapp.model.Movie;
import com.moviebookingapp.model.MoviePK;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, MoviePK> {

    @Query("{'MoviePK.movieName' : ?0}")
    List<Movie> findByMovieNameContaining(String name);

}
