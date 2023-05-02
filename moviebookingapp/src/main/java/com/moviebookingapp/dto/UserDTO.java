package com.moviebookingapp.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
@Getter
@Setter
public class UserDTO {
    private String loginId;


    private String email;

    private String firstName;
    private String lastName;

    private String password;
    private String contactNumber;

}




