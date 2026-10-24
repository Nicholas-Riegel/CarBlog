package com.car_blog.backend.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@NoArgsConstructor 
@Table(name = "cars")
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter 
    private String make;

    @Setter 
    private String model;

    @Setter 
    private Integer year;

    @Setter 
    private String description;

    @Setter 
    private Double price; 

    @Setter 
    private String imageUrl;

    private Instant createdAt;
    
    private Instant updatedAt;

    public Car(String make, String model, Integer year, String imageUrl){
        this.make = make;
        this.model = model;
        this.year = year;
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
}
