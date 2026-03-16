package com.example.MVC.Dtos.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private double price;
    private String categoryId;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

}
