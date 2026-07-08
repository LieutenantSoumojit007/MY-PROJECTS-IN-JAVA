package com.example.MyProject7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.MyProject7.Exception.ProductNotFoundException;
import com.example.MyProject7.model.Product;
import com.example.MyProject7.reposetory.ProductRepo;

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

    // Get Product By ID
    public Product getProductById(int id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product with ID " + id + " not found"));
    }

    // Get Product By Name
    public Product getProductByName(String name) {

        Product product = repo.findByName(name);

        if (product == null) {
            throw new ProductNotFoundException(
                    "Product with name '" + name + "' not found");
        }

        return product;
    }

    // Get Products By Place
    public List<Product> getProductsByPlace(String place) {

        List<Product> products = repo.findByPlace(place);

        if (products.isEmpty()) {
            throw new ProductNotFoundException(
                    "No products found in place : " + place);
        }

        return products;
    }

    // Out Of Warranty
    public List<Product> outOfWarranty(int year) {

        List<Product> products = repo.findByWarrantyLessThan(year);

        if (products.isEmpty()) {
            throw new ProductNotFoundException(
                    "No products found with warranty less than " + year);
        }

        return products;
    }

    // Search Product
    public List<Product> searchProducts(String text) {

        List<Product> products = repo.searchProducts(text);

        if (products.isEmpty()) {
            throw new ProductNotFoundException(
                    "No matching products found.");
        }

        return products;
    }

    // Update Product
    public Product updateProduct(Product product) {

        getProductById(product.getId());

        return repo.save(product);
    }

    // Delete Product
    public void deleteProduct(int id) {

        Product product = getProductById(id);

        repo.delete(product);
    }

    // Pagination
    public Page<Product> getProducts(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        return repo.findAll(pageable);
    }

    // Sort By Name
    public List<Product> sortByName() {

        return repo.findAll(Sort.by("name"));
    }

    // Sort By Warranty Descending
    public List<Product> sortByWarrantyDesc() {

        return repo.findAll(
                Sort.by(Sort.Direction.DESC, "warranty"));
    }

    // Dynamic Sorting
    public List<Product> sort(String field) {

        return repo.findAll(Sort.by(field));
    }

    // Pagination + Sorting
    public Page<Product> paginationAndSorting(int page,
                                              int size,
                                              String field) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(field));

        return repo.findAll(pageable);
    }
}