package com.example.MVC.Controller;

import com.example.MVC.Dtos.UserDto;
import com.example.MVC.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService; // inject the service

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
}