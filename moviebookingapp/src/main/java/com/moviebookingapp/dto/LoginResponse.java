package com.moviebookingapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private String username;
    private String password;
    private String role;
}
