package com.example.MVC.Implementation;


import com.example.MVC.Dtos.Request.ProductRequest;
import com.example.MVC.Dtos.Response.ProductResponse;
import com.example.MVC.Entities.Product;
import com.example.MVC.Mappers.ProductMapper;
import com.example.MVC.Services.ProductService;
import com.mongodb.client.MongoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImplementation implements ProductService {

    private final ProductMapper productMapper;
    private final MongoTemplate mongoTemplate;
    private final MongoClient mongo;


    @Override
    public List<ProductResponse> getProducts(List<String> categoryId, String sort) {
        Query query = new Query();

        if(categoryId != null && !categoryId.isEmpty()){
            query.addCriteria(Criteria.where("categoryId").in(categoryId));
        }
        if(sort == null || sort.isEmpty()){
            sort = "name";
        }
        query.with(Sort.by(Sort.Direction.ASC, sort));

        List<Product> products = mongoTemplate.find(query, Product.class, "Product");

        return products.stream().map(productMapper::toDto).toList();
    }

    @Override
    public ProductResponse getProductById(String Id) {
        Product product = mongoTemplate.findById(Id, Product.class, "Product");
        if(product == null) return null;
        return productMapper.toDto(product);
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.toEntity(productRequest);
        mongoTemplate.save(product, "Product");
        System.out.println(product);
        return productMapper.toDto(product);
    }


}
