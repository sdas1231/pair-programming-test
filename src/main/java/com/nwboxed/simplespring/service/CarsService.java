package com.nwboxed.simplespring.service;

import com.nwboxed.simplespring.dto.CarCountResponseDto;
import com.nwboxed.simplespring.dto.CarResponseDto;
import com.nwboxed.simplespring.exception.CarException;
import com.nwboxed.simplespring.model.Car;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

public interface CarsService {

    Car addCar(Car car);

    List<CarResponseDto> getCars();

    CarResponseDto getCar(String id);

    void deleteCar(String id);

    Car UpdateCar(String id, Car car);

    List<CarResponseDto> getCarByField(@NonNull final String colour);

    CarCountResponseDto getCarCount(@Nullable String colour, @Nullable String type) throws CarException;
}
