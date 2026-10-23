package com.car_blog.backend.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;

    private String model;

    private Integer year;

    private String description;

    private Double price; 

    private String imageUrl;

    private Instant createdAt;
    
    private Instant updatedAt;

    public Car(){}

    public Car(String make, String model, Integer year, String imageUrl){
        this.make = make;
        this.model = model;
        this.year = year;
        this.imageUrl = imageUrl;
    }

    // Field getters
    public Long getId(){
        return id;
    }

    public String getMake(){
        return make;
    }

    public String getModel(){
        return model;
    }

    public Integer getYear(){
        return year;
    }

    public String getDescription(){
        return description;
    }

    public Double getPrice(){
        return price;
    }

    public String getImageUrl(){
        return imageUrl;
    }
    
    // Field setters
    public void setMake(String make){
        this.make = make;
    }

    public void setModel(String model){
        this.model = model;
    }

    public void setYear(Integer year){
        this.year = year;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }
    
    // CreatedAt and UpdatedAt
    @PrePersist
    public void prePersist(){
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt = Instant.now();
    }

    public Instant getCreatedAt(){
        return createdAt;
    }

    public Instant getUpdatedAt(){
        return updatedAt;
    }
}
