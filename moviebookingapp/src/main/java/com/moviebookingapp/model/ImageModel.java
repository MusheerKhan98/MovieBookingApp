package com.moviebookingapp.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class ImageModel {

    @Id
    private String id;
    private String title;
    private Binary image;

}
