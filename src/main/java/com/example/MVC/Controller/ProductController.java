package com.example.MVC.Controller;

import com.example.MVC.Dtos.ProductDto;
import com.example.MVC.Entities.Product;
import com.example.MVC.Mappers.ProductMapper;
import com.example.MVC.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping("/products")
    public List<ProductDto> getAllProducts(
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) List<String> categoryId
    ) {
        Sort sortOrder = (sort !=null) ? Sort.by(sort) : Sort.by("name");

        List<Product> products;
        if (categoryId != null) {
            products = productRepository.findByCategoryIdIn(categoryId, sortOrder);
        } else {
            products = productRepository.findAll(sortOrder);
        }
        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }

    @GetMapping("/products/{id}")
        public ResponseEntity <ProductDto> getProductByID(@PathVariable String id){
           var product = productRepository.findById(id).orElse(null);

           if(product == null){
               return ResponseEntity.notFound().build();
           }
           var productDto = productMapper.toDto(product);
             return ResponseEntity.ok(productDto);
        }


}
