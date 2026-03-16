package com.example.MVC.Dtos.Request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {

    private String name;
    private String email;
    private String password;
    private String address;
    private String roleById;
    private String phoneNumber;
}
