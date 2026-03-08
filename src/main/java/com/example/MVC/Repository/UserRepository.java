package com.example.MVC.Repository;

import com.example.MVC.Entities.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByRoleById(String roleById, Sort sort);
    List<User> findByRoleByIdIn(List<String> roleById, Sort sort);


    //Find User base on email
    Optional<User> findByEmail(String email);
    // Find User base on ID
    Optional<User> findByName(String id);
}