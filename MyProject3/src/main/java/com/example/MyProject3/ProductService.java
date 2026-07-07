package com.example.MyProject3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProductService {

    @Autowired
    private ProductDao dao;

    public void addProduct(Product p){
        dao.save(p);
    }

    public List<Product> getAllProducts(){
        return dao.getAll();
    }

    public Product getProduct(String name){
        return dao.getProduct(name);
    }

    public List<Product> getProductsByPlace(String place){
        return dao.findAllProductByPlace(place);
    }

    public List<Product> outOfWarranty(int year){
        return dao.OutOfWarrenty(year);
    }

    public List<Product> search(String text){
        return dao.findAllByText(text);
    }
}
