package com.example.MVC.Repository;

import com.example.MVC.Entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {


    //Find User base on email
    Optional<User> findByEmail(String email);
    // Find User base on ID
    Optional<User> findByName(String id);
}