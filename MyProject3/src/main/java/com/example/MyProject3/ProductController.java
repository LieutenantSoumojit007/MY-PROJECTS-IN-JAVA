package com.example.MyProject3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
	 @Autowired
	    ProductService service;

	    @PostMapping
	    public String addProduct(@RequestBody Product p){
	        service.addProduct(p);
	        return "Product Added Successfully";
	    }

	    @GetMapping
	    public List<Product> getAllProducts(){
	        return service.getAllProducts();
	    }

	    @GetMapping("/{name}")
	    public Product getProduct(@PathVariable String name){
	        return service.getProduct(name);
	    }

	    @GetMapping("/place/{place}")
	    public List<Product> getByPlace(@PathVariable String place){
	        return service.getProductsByPlace(place);
	    }

	    @GetMapping("/warranty/{year}")
	    public List<Product> outOfWarranty(@PathVariable int year){
	        return service.outOfWarranty(year);
	    }

	    @GetMapping("/search/{text}")
	    public List<Product> search(@PathVariable String text){
	        return service.search(text);
	    }
	    
/*	    | Method | URL                                            | Description           |
	    | ------ | ---------------------------------------------- | --------------------- |
	    | POST   | `http://localhost:8080/products`               | Add Product           |
	    | GET    | `http://localhost:8080/products`               | Get All Products      |
	    | GET    | `http://localhost:8080/products/Lenovo`        | Get Product by Name   |
	    | GET    | `http://localhost:8080/products/place/Desk`    | Get Products by Place |
	    | GET    | `http://localhost:8080/products/warranty/2026` | Get Expired Products  |
	    | GET    | `http://localhost:8080/products/search/laptop` | Search by Keyword     |
*/
}
