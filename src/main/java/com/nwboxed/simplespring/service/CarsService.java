package com.nwboxed.simplespring.service;

import com.nwboxed.simplespring.dto.CarResponseDto;
import com.nwboxed.simplespring.model.Car;

import java.util.List;

public interface CarsService {

    Car addCar(Car car);

    List<CarResponseDto> getCars();

    CarResponseDto getCar(String id);

    void deleteCar(String id);

    Car UpdateCar(String id, Car car);
}
