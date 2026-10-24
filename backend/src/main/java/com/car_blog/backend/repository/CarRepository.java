package com.car_blog.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car_blog.backend.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

    
} 
