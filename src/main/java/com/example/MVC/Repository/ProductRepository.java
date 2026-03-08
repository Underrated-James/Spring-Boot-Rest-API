package com.example.MVC.Repository;


import com.example.MVC.Entities.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByCategoryId(String categoryId, Sort sort);
    List<Product> findByCategoryIdIn(List<String> categoryIds, Sort sort);
}
