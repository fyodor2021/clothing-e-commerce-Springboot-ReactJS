package com.finefoods.productmicroservice.repository;

import com.finefoods.productmicroservice.dto.ProductResponse;
import com.finefoods.productmicroservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findProductById(Long id);
    List<Product> findProductByCategory(String category);

    List<Product> findByNameContainingIgnoreCase(String word);

    void deleteById(Long id);
}

