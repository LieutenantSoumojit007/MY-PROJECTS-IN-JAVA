package com.example.MyProject6.reporetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.MyProject6.model.Product;


public interface ProductRepo extends JpaRepository<Product, Integer> {

    Product findByName(String name);

    List<Product> findByPlace(String place);

    List<Product> findByWarrantyLessThan(int year);

    @Query("""
    SELECT p
    FROM Product p
    WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :text, '%'))
       OR LOWER(p.type) LIKE LOWER(CONCAT('%', :text, '%'))
       OR LOWER(p.place) LIKE LOWER(CONCAT('%', :text, '%'))
    """)
    List<Product> searchProducts(@Param("text") String text);
}