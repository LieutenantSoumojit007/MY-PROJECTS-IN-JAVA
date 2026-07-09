package com.example.MyProject8.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.MyProject8.model.Product;
import com.example.MyProject8.service.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService service;

    // ==========================
    // ADD PRODUCT WITH IMAGE
    // ==========================

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product addProduct(
            @RequestParam("name") String name,
            @RequestParam("type") String type,
            @RequestParam("place") String place,
            @RequestParam("warranty") int warranty,
            @RequestParam("image") MultipartFile image)
            throws IOException {

        Product product = new Product();

        product.setName(name);
        product.setType(type);
        product.setPlace(place);
        product.setWarranty(warranty);

        return service.addProduct(product, image);
    }

    // ==========================
    // GET ALL PRODUCTS
    // ==========================

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    // ==========================
    // GET PRODUCT BY ID
    // ==========================

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {
        return service.getProductById(id);
    }

    // ==========================
    // GET PRODUCT BY NAME
    // ==========================

    @GetMapping("/name/{name}")
    public Product getProductByName(@PathVariable String name) {
        return service.getProductByName(name);
    }

    // ==========================
    // GET PRODUCTS BY PLACE
    // ==========================

    @GetMapping("/place/{place}")
    public List<Product> getProductsByPlace(@PathVariable String place) {
        return service.getProductsByPlace(place);
    }

    // ==========================
    // OUT OF WARRANTY
    // ==========================

    @GetMapping("/warranty/{year}")
    public List<Product> outOfWarranty(@PathVariable int year) {
        return service.outOfWarranty(year);
    }

    // ==========================
    // SEARCH
    // ==========================

    @GetMapping("/search/{text}")
    public List<Product> search(@PathVariable String text) {
        return service.searchProducts(text);
    }

    // ==========================
    // UPDATE PRODUCT
    // ==========================

    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable int id,
            @RequestBody Product product) {

        product.setId(id);

        return service.updateProduct(product);
    }

    // ==========================
    // UPDATE IMAGE
    // ==========================

    @PutMapping(value = "/{id}/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product updateImage(
            @PathVariable int id,
            @RequestParam("image") MultipartFile image)
            throws IOException {

        return service.updateImage(id, image);
    }

    // ==========================
    // DELETE
    // ==========================

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {

        service.deleteProduct(id);

        return "Product Deleted Successfully";
    }

    // ==========================
    // DOWNLOAD IMAGE
    // ==========================

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable int id) {

        Product product = service.getProductWithImage(id);

        return ResponseEntity.ok()

                .contentType(MediaType.parseMediaType(product.getImageType()))

                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" +
                                product.getImageName() + "\"")

                .body(product.getImageData());

    }

    // ==========================
    // PAGINATION
    // ==========================

    @GetMapping("/page")
    public Page<Product> pagination(
            @RequestParam int page,
            @RequestParam int size) {

        return service.getProducts(page, size);
    }

    // ==========================
    // SORT
    // ==========================

    @GetMapping("/sort")
    public List<Product> sort(
            @RequestParam String field) {

        return service.sort(field);
    }

    // ==========================
    // PAGINATION + SORT
    // ==========================

    @GetMapping("/pageSort")
    public Page<Product> pageSort(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String field) {

        return service.paginationAndSorting(
                page,
                size,
                field);

    }

}