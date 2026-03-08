package com.example.MVC.Mappers;


import com.example.MVC.Dtos.ProductDto;
import com.example.MVC.Entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    ProductDto toDto(Product product);
}
