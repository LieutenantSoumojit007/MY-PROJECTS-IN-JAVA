package com.example.MyProject7.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Product name cannot be empty")
    private String name;

    @NotBlank(message = "Product type cannot be empty")
    private String type;

    @NotBlank(message = "Product place cannot be empty")
    private String place;

    @Min(value = 2000, message = "Warranty year must be valid")
    private int warranty;

    // Default Constructor
    public Product() {
    }

    // Parameterized Constructor
    public Product(int id, String name, String type, String place, int warranty) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.place = place;
        this.warranty = warranty;
    }

    // Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    @Override
    public String toString() {
        return "Product [id=" + id +
                ", name=" + name +
                ", type=" + type +
                ", place=" + place +
                ", warranty=" + warranty + "]";
    }
}