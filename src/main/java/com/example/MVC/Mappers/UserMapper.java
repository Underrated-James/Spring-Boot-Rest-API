package com.example.MVC.Mappers;


import com.example.MVC.Dtos.Request.RegisterUserRequest;
import com.example.MVC.Dtos.Response.UserDto;
import com.example.MVC.Entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserDto toDto(User user);
    
    User toEntity(RegisterUserRequest request);
}
