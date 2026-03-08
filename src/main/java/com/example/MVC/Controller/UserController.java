package com.example.MVC.Controller;

import com.example.MVC.Dtos.UserDto;
import com.example.MVC.Entities.User;
import com.example.MVC.Mappers.UserMapper;
import com.example.MVC.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserRepository UserRepository;
    private final UserMapper UserMapper;

    @GetMapping("/users")
    public List<UserDto> getAllUsers(
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) List<String> roleById
    ){
        Sort sortOrder = (sort != null && sort.isBlank()) ? Sort.by(sort) : Sort.by("name");

        List<User> user;
        if(roleById != null){
            user = UserRepository.findByRoleByIdIn(roleById, sortOrder);
        } else {
            user = UserRepository.findAll(sortOrder);
        }

        return user.stream()
                .map(UserMapper::toDto)
                .toList();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserByID(@PathVariable String id){
       var user = UserRepository.findById(id).orElse(null);

       if(user == null){
           return ResponseEntity.notFound().build();
       }
       var userDto = UserMapper.toDto(user);
         return ResponseEntity.ok(userDto);
    }


}
