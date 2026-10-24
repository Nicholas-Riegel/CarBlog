package com.car_blog.backend.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.car_blog.backend.entity.Car;
import com.car_blog.backend.repository.CarRepository;

@Service
public class CarService {

    private final CarRepository carRepo;

    public CarService(CarRepository carRepo){
        this.carRepo = carRepo;
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
        carToMod.setMake(carIn.getMake());
        carToMod.setModel(carIn.getModel());
        carToMod.setYear(carIn.getYear());
        carToMod.setDescription(carIn.getDescription());
        carToMod.setPrice(carIn.getPrice());
        carToMod.setImageUrl(carIn.getImageUrl());
        return carRepo.save(carToMod);
    }

    public void deleteCar(Long id){
        if (!carRepo.existsById(id)){
            throw carNotFound();
        }
        carRepo.deleteById(id);
    }

    private Car findCarOrThrow(Long id){
        return carRepo.findById(id).orElseThrow(this::carNotFound); // = () -> carNotFound()
    }

    private ResponseStatusException carNotFound(){
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found.");
    }
}