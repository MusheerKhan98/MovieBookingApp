package com.moviebookingapp.service;

import com.moviebookingapp.model.User;
import com.moviebookingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId){
      Optional<User> userOptional = userRepository.findById(loginId);
        return userOptional.map(GroupUserDetails::new).
                orElseThrow(()-> new UsernameNotFoundException("Username doesn't exist"));
    }



}
