package com.example.MyProject5.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MyProject5.Exception.ProductNotFoundException;
import com.example.MyProject5.model.Product;
import com.example.MyProject5.reposetry.ProductRepo;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    // Add Product
    public Product addProduct(Product product) {
        return repo.save(product);
    }

    // Get All Products
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id)
                .orElseThrow(() ->
                new ProductNotFoundException(
                                "Product with ID " + id + " not found"));
    }

    // Get Product by Name
    public Product getProductByName(String name) {
        return repo.findByName(name);
    }

    // Get Products by Place
    public List<Product> getProductsByPlace(String place) {
        return repo.findByPlace(place);
    }

    // Out of Warranty
    public List<Product> outOfWarranty(int year) {
        return repo.findByWarrantyLessThan(year);
    }

    // Search Product
    public List<Product> searchProducts(String text) {
        return repo.searchProducts(text);
    }

    public Product updateProduct(Product product) {
        getProductById(product.getId());
        return repo.save(product);
    }

    public void deleteProduct(int id) {
        Product product = getProductById(id);
        repo.delete(product);
    }
}