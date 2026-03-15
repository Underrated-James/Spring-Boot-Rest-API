// UserService.java
package com.example.MVC.Services;

import com.example.MVC.Dtos.Request.UserRequest;
import com.example.MVC.Dtos.Response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getUsers(List<String> roles, String sort);

    UserResponse getUserById(String id);

    UserResponse createUser(UserRequest userRequest);
}