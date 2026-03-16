package com.example.MVC.Dtos.Request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRequest {
    private String id;
    private String name;
    private String description;
    private double price;
    private String categoryId;
    private String location;
}
