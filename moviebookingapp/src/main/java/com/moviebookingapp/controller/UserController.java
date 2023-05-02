package com.moviebookingapp.controller;

import com.moviebookingapp.custom.exception.InvalidPasswordException;
import com.moviebookingapp.custom.exception.UnregisteredUserException;
import com.moviebookingapp.custom.exception.UserAlreadyExistsException;
import com.moviebookingapp.custom.exception.UserNotFoundException;
import com.moviebookingapp.dto.LoginDTO;
import com.moviebookingapp.dto.UserDTO;
import com.moviebookingapp.model.User;
import com.moviebookingapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1.0/moviebooking")
public class UserController {
    Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    /**
     * Registering a new User
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody UserDTO userDTO) throws UserAlreadyExistsException {
        log.info("Etering UserController : register");
        User user = new User();
        user.setLoginId(userDTO.getLoginId());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setContactNumber(userDTO.getContactNumber());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRole("customer");
        Boolean userCreated = userService.register(user);
        return  ResponseEntity.ok(userCreated);
    }

    /**
     * Allowing a registered user to sign in
     *
     * @param loginCredentials
     * @return String
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginCredentials) throws UnregisteredUserException, InvalidPasswordException {
        log.info("Etering UserController : login");
        return ResponseEntity.ok(userService.authenticate(loginCredentials));

    }

    /**
     * Allowing a registered user to reset passsword
     *
     * @param loginId
     * @param user
     * @return String
     */
    @PutMapping(value = "/{loginId}/forgot" , produces = "application/json")
    public ResponseEntity<String> updatePassword(@PathVariable String loginId, @RequestBody String newPassword)
    throws UserNotFoundException {
        log.info("Etering UserController : updatePassword");
        User u = userService.updatePassword(loginId, newPassword);
        log.info("Password Updated for user "+u);
        return ResponseEntity.ok("Password Updated for user "+u.getLoginId());
    }
}
