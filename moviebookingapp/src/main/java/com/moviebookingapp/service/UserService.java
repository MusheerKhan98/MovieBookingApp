package com.moviebookingapp.service;

import com.moviebookingapp.custom.exception.InvalidPasswordException;
import com.moviebookingapp.custom.exception.UnregisteredUserException;
import com.moviebookingapp.custom.exception.UserAlreadyExistsException;
import com.moviebookingapp.custom.exception.UserNotFoundException;
import com.moviebookingapp.dto.LoginDTO;
import com.moviebookingapp.dto.LoginResponse;
import com.moviebookingapp.model.User;

public interface UserService {
    boolean register(User user) throws UserAlreadyExistsException;

    String authenticate(LoginDTO loginCredentials) throws UnregisteredUserException, InvalidPasswordException;

    User updatePassword(String loginId, String user) throws UserNotFoundException;
}
