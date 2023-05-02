package com.moviebookingapp.repository;

import com.moviebookingapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User,String> {
    @Query("{'loginId' : ?0}")
    User findByloginId(String loginId);
    @Query(value="{'loginId' : ?0}", delete=true)
    void deleteByloginId(String loginId);
}
