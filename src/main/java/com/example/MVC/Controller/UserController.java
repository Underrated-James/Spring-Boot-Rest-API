package com.example.MVC.Controller;

import com.example.MVC.Dtos.Request.RegisterUserRequest;
import com.example.MVC.Dtos.Response.UserDto;
import com.example.MVC.Mappers.UserMapper;
import com.example.MVC.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService; // inject the service
    private final UserMapper userMapper;

    // GET /api/users?roleById=ADMIN&sort=name
    @GetMapping("/users")
    public List<UserDto> getAllUsers(
            @RequestHeader(name = "x-auth-token", required = false) String authToken,
            @RequestParam(required = false, name = "roleById") List<String> roles,
            @RequestParam(required = false, defaultValue = "name") String sort
    ) {
        System.out.println("Received auth token: " + authToken); // log the token for debugging
        return userService.getUsers(roles, sort); // delegate to service
    }

    // GET /api/users/{id}
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserByID(@PathVariable String id) {
        UserDto userDto = userService.getUserById(id);
        if(userDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDto); // delegate to service
    }

    //Post Create User
    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(
            @RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        System.out.println("Received create user request: " + request); // log the request for debugging
        UserDto createdUser = userService.createUser(request);
        var uri  = uriComponentsBuilder.path("/users/{id}").buildAndExpand(createdUser.getId());
        System.out.println("Creating user: " + createdUser); // log the user for debugging
        return ResponseEntity.created(uri.toUri()).body(createdUser); // delegate to service
    }
}