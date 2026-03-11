package com.example.MVC.Repository;

import com.example.MVC.Entities.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {


    List<User> findByRoleByIdIn(List<String> roleById, Sort sort);
}