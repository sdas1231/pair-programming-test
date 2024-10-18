package com.nwboxed.simplespring.controller;

import com.nwboxed.simplespring.dto.CarDto;
import com.nwboxed.simplespring.dto.CarResponseDto;
import com.nwboxed.simplespring.helper.CarServiceHelper;
import com.nwboxed.simplespring.service.CarsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class CarController {

    private CarsService carsService;
    private CarServiceHelper carServiceHelper;

    @GetMapping("")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome to Car Application");
    }

    @PostMapping("/cars")
    public ResponseEntity<CarResponseDto> addCar(@RequestBody CarDto car) {
        return new ResponseEntity<>(carServiceHelper.addCar(car), HttpStatus.CREATED);
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarResponseDto>> getCars() {
        return ResponseEntity.ok(carsService.getCars());
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<CarResponseDto> getCar(@PathVariable String id) {
        return ResponseEntity.ok(carsService.getCar(id));
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<CarResponseDto> deleteCar(@PathVariable String id) {
        carsService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/cars/{id}")
    public ResponseEntity<CarResponseDto> updateCar(@PathVariable String id, @RequestBody CarDto car) {
        return ResponseEntity.ok(carServiceHelper.UpdateCar(id, car));
    }
}
