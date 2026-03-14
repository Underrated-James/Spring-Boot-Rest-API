// UserService.java
package com.example.MVC.Services;

import com.example.MVC.Dtos.Request.RegisterUserRequest;
import com.example.MVC.Dtos.Response.UserDto;
import java.util.List;

public interface UserService {
    List<UserDto> getUsers(List<String> roles, String sort);

    UserDto getUserById(String id);

    UserDto createUser(RegisterUserRequest request);
}