package com.example.MVC.Services;

import com.example.MVC.Dtos.Request.ProductRequest;
import com.example.MVC.Dtos.Response.ProductResponse;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {
    //Get All Products
    List<ProductResponse> getProducts(List<String> categoryId, String sort);
    //Get Product By Id
    ProductResponse getProductById(String Id);
    //Create Product
    ProductResponse createProduct(ProductRequest productRequest);
}
