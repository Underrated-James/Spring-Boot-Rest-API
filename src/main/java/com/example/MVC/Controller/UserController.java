package com.example.MVC.Controller;

import com.example.MVC.Entities.User;
import com.example.MVC.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserRepository UserRepository;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return UserRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserByID(@PathVariable String id){
       return  UserRepository.findById(id).orElse(null);
    }


}
