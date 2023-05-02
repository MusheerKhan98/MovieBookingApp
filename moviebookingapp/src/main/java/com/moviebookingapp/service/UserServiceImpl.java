package com.moviebookingapp.service;

import com.moviebookingapp.custom.exception.InvalidPasswordException;
import com.moviebookingapp.custom.exception.UnregisteredUserException;
import com.moviebookingapp.custom.exception.UserAlreadyExistsException;
import com.moviebookingapp.custom.exception.UserNotFoundException;
import com.moviebookingapp.dto.LoginDTO;
import com.moviebookingapp.dto.LoginResponse;
import com.moviebookingapp.model.User;
import com.moviebookingapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements  UserService {

    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean register(User user) throws UserAlreadyExistsException {
        log.info("Entering UserService: register");
        if(userRepository.findById(user.getLoginId()).isPresent()){
            throw new UserAlreadyExistsException("You are trying to add an existing user");
        }
        else {
            userRepository.save(user);
            return true;
        }
    }

    @Override
    public String authenticate(LoginDTO loginCredentials) throws UnregisteredUserException, InvalidPasswordException {
        log.info("Entering UserService: authenticate");
        log.info(loginCredentials.getUsername());
        if (userRepository.findById(loginCredentials.getUsername()).isEmpty()) {
            throw new UnregisteredUserException("User not registered");
        } else {
            User user = userRepository.findById(loginCredentials.getUsername()).get();
            if(passwordEncoder.matches(loginCredentials.getPassword(),user.getPassword())){
                return userRepository.findById(loginCredentials.getUsername()).get().getRole();
            } else
                throw new InvalidPasswordException("Invalid password");
        }
    }
    @Override
    public User updatePassword(String loginId,String user) throws UserNotFoundException {
        log.info("Entering UserService: updatePassword");
       Optional<User> userOptional= userRepository.findById(loginId);
       if(userOptional.isPresent()){
           User newUser= userOptional.get();
           newUser.setPassword(passwordEncoder.encode(user));
           User updatedUser = userRepository.save(newUser);
           return updatedUser;
       }
       else
            throw new UserNotFoundException("User not fount");

    }
}
