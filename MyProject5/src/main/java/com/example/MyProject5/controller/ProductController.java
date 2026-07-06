package com.example.MyProject5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MyProject5.model.Product;
import com.example.MyProject5.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService service;

    // Add Product
    @PostMapping
    public Product addProduct(@Valid @RequestBody Product product) {

        return service.addProduct(product);
    }

    // Get All Products
    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    // Get Product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return service.getProductById(id);
    }

    // Get Product by Name
    @GetMapping("/name/{name}")
    public Product getProductByName(@PathVariable String name) {
        return service.getProductByName(name);
    }

    // Get Products by Place
    @GetMapping("/place/{place}")
    public List<Product> getProductsByPlace(@PathVariable String place) {
        return service.getProductsByPlace(place);
    }

    // Out of Warranty
    @GetMapping("/warranty/{year}")
    public List<Product> getOutOfWarranty(@PathVariable int year) {
        return service.outOfWarranty(year);
    }

    // Search Product
    @GetMapping("/search/{text}")
    public List<Product> searchProducts(@PathVariable String text) {
        return service.searchProducts(text);
    }

    // Update Product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id,
                                 @RequestBody Product product) {

        product.setId(id);
        return service.updateProduct(product);
    }

    // Delete Product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {

        service.deleteProduct(id);

        return "Product Deleted Successfully";
    }

}