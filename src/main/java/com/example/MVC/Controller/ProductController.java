package com.example.MVC.Controller;


import com.example.MVC.Dtos.Request.ProductRequest;
import com.example.MVC.Dtos.Response.ProductResponse;
import com.example.MVC.Entities.Product;
import com.example.MVC.Mappers.ProductMapper;
import com.example.MVC.Repository.ProductRepository;
import com.example.MVC.Services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;


    @GetMapping("/products")
    public List<ProductResponse> getAllProducts (
            @RequestParam(required = false, name = "categoryId") List<String> categoryId,
            @RequestParam(required = false, defaultValue = "name") String sort
    ){
        System.out.println(categoryId + sort);
        return productService.getProducts(categoryId, sort);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String id){
        ProductResponse productResponse = productService.getProductById(id);
        if(productResponse == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productResponse);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponse> createProduct(
            @RequestBody ProductRequest productRequest,
            UriComponentsBuilder uriComponentsBuilder
    ){
        //Create the Product
        ProductResponse createdproduct = productService.createProduct(productRequest);
        //Build the URI for the created product
        var uri = uriComponentsBuilder.path("/api/products/{id}").buildAndExpand(createdproduct.getId()).toUri();
        //Return the created product with the URI 201 Created
        return ResponseEntity.created(uri).body(createdproduct);
    }

}
