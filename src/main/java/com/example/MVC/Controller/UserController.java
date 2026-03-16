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

        /**
     * Retrieves a list of users, with optional filtering by role and sorting.
     * <p>
     * This endpoint handles GET requests to "/users" and returns a list of {@link UserResponse} objects.
     * It allows for filtering users by their roles and sorting the results based on a specified field.
     * An example request might look like: {@code GET /api/users?roleById=ADMIN&sort=name}
     *
     * @param authToken An optional authentication token passed in the "x-auth-token" request header.
     *                  It is currently logged for debugging purposes.
     * @param roles     An optional list of role names to filter the users by. The query parameter is "roleById".
     *                  For example, {@code ?roleById=ADMIN&roleById=USER}.
     * @param sort      An optional parameter to specify the sorting field for the user list.
     *                  Defaults to "name" if not provided.
     * @return A list of {@link UserResponse} objects representing the users that match the filter criteria,
     *         sorted as requested.
     */
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
}