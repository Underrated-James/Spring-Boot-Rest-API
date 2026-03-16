package com.example.MVC.Controller;

import com.example.MVC.Dtos.Request.UserRequest;
import com.example.MVC.Dtos.Response.UserResponse;
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

    // GET /api/users?roleById=ADMIN&sort=name
    @GetMapping("/users")
    public List<UserResponse> getAllUsers(
            @RequestHeader(name = "x-auth-token", required = false) String authToken,
            @RequestParam(required = false, name = "roleById") List<String> roles,
            @RequestParam(required = false, defaultValue = "name") String sort
    ) {
        System.out.println("Received auth token: " + authToken); // log the token for debugging
        return userService.getUsers(roles, sort); // delegate to service
    }

    // GET /api/users/{id}
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserByID(@PathVariable String id) {
        UserResponse userDto = userService.getUserById(id);
        if(userDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDto); // delegate to service
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(
            @RequestBody UserRequest userRequest,
            UriComponentsBuilder uriComponentBuilder
    ) {
        // Create the user
        UserResponse createdUser = userService.createUser(userRequest);

        // Build the URI of the newly created user
        var uri = uriComponentBuilder
                .path("/api/users/{id}")
                .buildAndExpand(createdUser.getId()) // use the generated ID
                .toUri();

        // Return ResponseEntity with 201 Created and Location header
        return ResponseEntity
                .created(uri)   // sets HTTP 201 + Location
                .body(createdUser); // response body
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable String id,
            @RequestBody UserRequest userRequest){
        UserResponse user = userService.updateUser(id, userRequest);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}