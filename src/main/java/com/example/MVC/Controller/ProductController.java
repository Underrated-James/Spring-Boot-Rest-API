package com.example.MVC.Controller;


import com.example.MVC.Dtos.ProductDto;
import com.example.MVC.Entities.Product;
import com.example.MVC.Mappers.ProductMapper;
import com.example.MVC.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @GetMapping("/products")
    public List<ProductDto> getAllProducts (
            @RequestParam(required = false, name = "categoryId") List<String> categoryId,
            @RequestParam(required = false) String sort
    ){
        List<Product> products;
        if(categoryId == null || categoryId.isEmpty()){
            products = productRepository.findAll();
        }else {
            products = productRepository.findByCategoryIdIn(categoryId);
        }

        return products.stream().map(productMapper::toDto).toList();
    }
}
