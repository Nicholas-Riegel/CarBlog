package com.car_blog.backend.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController 
public class CarController {

    @GetMapping("/api/cars")
    public String getCars() {
        return "Cars will go here!";
    }
}
