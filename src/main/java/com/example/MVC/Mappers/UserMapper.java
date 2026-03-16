package com.example.MVC.Mappers;


import com.example.MVC.Dtos.Request.UserRequest;
import com.example.MVC.Dtos.Response.UserResponse;
import com.example.MVC.Entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserResponse toDto(User user);

    @Mapping(target = "id", ignore = true) // ignore ID
    User toEntity(UserRequest userRequest);
}
