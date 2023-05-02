package com.moviebookingapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class User {

    @Id
    @Indexed(unique = true)
    private String loginId;

    @Indexed(unique = true)
    private String email;

    private String firstName;
    private String lastName;

    private String password;
    private String contactNumber;

    private String role;
}
