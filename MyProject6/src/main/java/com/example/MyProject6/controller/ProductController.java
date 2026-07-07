package com.example.MyProject6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MyProject6.model.Product;
import com.example.MyProject6.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/products")
@Tag(name = "Product API", description = "Operations related to Product Management")
public class ProductController {

    @Autowired
    private ProductService service;

    // Add Product
    @Operation(summary = "Add a new Product")
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return service.addProduct(product);
    }

    // Get All Products
    @Operation(summary = "Get All Products")
    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    // Get Product by ID
    @Operation(summary = "Get Product By ID")
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {
        return service.getProductById(id);
    }

    // Get Product by Name
    @Operation(summary = "Get Product By Name")
    @GetMapping("/name/{name}")
    public Product getProductByName(@PathVariable String name) {

        return service.getProductByName(name);

    }

    // Get Products by Place
    @Operation(summary = "Get Products By Place")
    @GetMapping("/place/{place}")
    public List<Product> getProductsByPlace(@PathVariable String place) {

        return service.getProductsByPlace(place);

    }

    // Out of Warranty
    @Operation(summary = "Get Products Whose Warranty Is Less Than the Given Year")
    @GetMapping("/warranty/{year}")
    public List<Product> getOutOfWarranty(@PathVariable int year) {

        return service.outOfWarranty(year);

    }

    // Search Product
    @Operation(summary = "Search Products")
    @GetMapping("/search/{text}")
    public List<Product> search(@PathVariable String text) {
        return service.searchProducts(text);
    }

    // Update Product
    @Operation(summary = "Update Product")
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id,
                                 @RequestBody Product product) {

        product.setId(id);
        return service.updateProduct(product);
    }

    // Delete Product
    @Operation(summary = "Delete Product")
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {

        service.deleteProduct(id);
        return "Product Deleted Successfully";
    }

}