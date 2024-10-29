package com.nwboxed.simplespring.controller;

import com.nwboxed.simplespring.client.CarApiClient;
import com.nwboxed.simplespring.dto.CarCountResponseDto;
import com.nwboxed.simplespring.dto.CarDto;
import com.nwboxed.simplespring.dto.CarResponseDto;
import com.nwboxed.simplespring.helper.CarServiceHelper;
import com.nwboxed.simplespring.service.CarsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
public class CarController {

    private CarsService carsService;
    private CarServiceHelper carServiceHelper;
    private CarApiClient carApiClient;

    @GetMapping("")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome to Car Application");
    }

    @PostMapping("/cars")
    public ResponseEntity<CarResponseDto> addCar(@RequestBody @Valid CarDto car) {
        return new ResponseEntity<>(carServiceHelper.addCar(car), HttpStatus.CREATED);
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarResponseDto>> getCars(@Nullable @RequestParam("colour") final String colour) {
        if (Objects.isNull(colour))
            return ResponseEntity.ok(carsService.getCars());
        return ResponseEntity.ok(carsService.getCarByField(colour));
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

    @GetMapping("/cars/count")
    public ResponseEntity<CarCountResponseDto> getCarCount(@Nullable @RequestParam("colour") final String colour,
                                                           @Nullable @RequestParam("type") final String type) {

        return ResponseEntity.ok(carsService.getCarCount(colour, type));
    }
}
