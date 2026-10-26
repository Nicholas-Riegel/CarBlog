package com.car_blog.backend.service;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.car_blog.backend.entity.Car;
import com.car_blog.backend.repository.CarRepository;


@Service
public class CarService {

    private final CarRepository carRepo;
    private final ImageService imageService;

    public CarService(CarRepository carRepo, ImageService imageService){
        this.carRepo = carRepo;
        this.imageService = imageService;
    }

    public List<Car> getAllCars(){
        return carRepo.findAll();
    }

    public Car getCarById(Long id){
        return findCarOrThrow(id);
    }

    public Car createCar(Car carIn){
        return carRepo.save(carIn);
    }

    public Car updateCar(Long id, Car carIn){
        
        Car carToMod = findCarOrThrow(id);
        String oldImageUrl = carToMod.getImageUrl();

        carToMod.setMake(carIn.getMake());
        carToMod.setModel(carIn.getModel());
        carToMod.setYear(carIn.getYear());
        carToMod.setDescription(carIn.getDescription());
        carToMod.setPrice(carIn.getPrice());
        carToMod.setImageUrl(carIn.getImageUrl());
        
        Car saved = carRepo.save(carToMod);

        if (!Objects.equals(oldImageUrl, carIn.getImageUrl())) {
            imageService.deleteImage(oldImageUrl);
        }

        return saved;
    }

    public void deleteCar(Long id){
        Car car = findCarOrThrow(id);
        carRepo.deleteById(id);
        imageService.deleteImage(car.getImageUrl());
    }

    // Helper method
    private Car findCarOrThrow(Long id){
        return carRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found."));
    }
}