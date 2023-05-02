package com.moviebookingapp.repository;

import com.moviebookingapp.model.ImageModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<ImageModel,String> {
}
