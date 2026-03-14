package com.example.MVC.Dtos.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RegisterUserRequest {

    private String name;
    private String email;
    private String password;
    private String roleById;
    private String phoneNumber;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
}
