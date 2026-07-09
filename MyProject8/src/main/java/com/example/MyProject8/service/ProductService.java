package com.example.MyProject8.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.MyProject8.exception.ProductNotFoundException;
import com.example.MyProject8.model.Product;
import com.example.MyProject8.reposetorry.ProductRepo;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    // ==========================
    // CREATE PRODUCT
    // ==========================

    public Product addProduct(Product product) {
        return repo.save(product);
    }

    public Product addProduct(Product product, MultipartFile image) throws IOException {

        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());

        return repo.save(product);
    }

    // ==========================
    // READ PRODUCTS
    // ==========================

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(int id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product with ID " + id + " not found"));
    }

    public Product getProductByName(String name) {

        Product product = repo.findByName(name);

        if (product == null) {
            throw new ProductNotFoundException(
                    "Product with name '" + name + "' not found");
        }

        return product;
    }

    public List<Product> getProductsByPlace(String place) {

        List<Product> products = repo.findByPlace(place);

        if (products.isEmpty()) {
            throw new ProductNotFoundException(
                    "No products found in place : " + place);
        }

        return products;
    }

    public List<Product> outOfWarranty(int year) {

        List<Product> products = repo.findByWarrantyLessThan(year);

        if (products.isEmpty()) {
            throw new ProductNotFoundException(
                    "No products found with warranty less than " + year);
        }

        return products;
    }

    public List<Product> searchProducts(String text) {

        List<Product> products = repo.searchProducts(text);

        if (products.isEmpty()) {
            throw new ProductNotFoundException(
                    "No matching products found.");
        }

        return products;
    }

    // ==========================
    // UPDATE
    // ==========================

    public Product updateProduct(Product product) {

        getProductById(product.getId());

        return repo.save(product);
    }

    public Product updateImage(int id, MultipartFile image)
            throws IOException {

        Product product = getProductById(id);

        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());

        return repo.save(product);
    }

    // ==========================
    // DELETE
    // ==========================

    public void deleteProduct(int id) {

        Product product = getProductById(id);

        repo.delete(product);
    }

    // ==========================
    // IMAGE
    // ==========================

    public byte[] getImage(int id) {

        Product product = getProductById(id);

        return product.getImageData();
    }

    public Product getProductWithImage(int id) {

        return getProductById(id);
    }

    // ==========================
    // PAGINATION
    // ==========================

    public Page<Product> getProducts(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        return repo.findAll(pageable);
    }

    // ==========================
    // SORTING
    // ==========================

    public List<Product> sortByName() {

        return repo.findAll(Sort.by("name"));
    }

    public List<Product> sortByWarrantyDesc() {

        return repo.findAll(
                Sort.by(Sort.Direction.DESC, "warranty"));
    }

    public List<Product> sort(String field) {

        return repo.findAll(Sort.by(field));
    }

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