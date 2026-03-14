package com.example.MVC.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"name", "phoneNumber"})
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String roleById;
    private LocalDateTime createdAt;

}