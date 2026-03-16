package com.example.MVC.Mappers;

import com.example.MVC.Dtos.Request.ProductRequest;
import com.example.MVC.Dtos.Response.ProductResponse;
import com.example.MVC.Entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categoryId", source = "categoryId")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    ProductResponse toDto(Product product);

    @Mapping(target = "id", ignore = true)
    Product toEntity(ProductRequest productRequest);
}
