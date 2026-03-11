package com.example.MVC.Dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDto {
    private String id;
    private String name;
    private String description;
    private double price;
    private String categoryId;
    private LocalDateTime createdAt;

}
