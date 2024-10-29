package com.nwboxed.simplespring.helper;

import com.nwboxed.simplespring.dto.CarDto;
import com.nwboxed.simplespring.dto.CarResponseDto;
import com.nwboxed.simplespring.model.Car;
import com.nwboxed.simplespring.service.CarsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class CarServiceHelper {

    private CarsService carsService;

    public CarResponseDto addCar(CarDto carDto) {
        Car car = new Car();
        BeanUtils.copyProperties(carDto, car);

        return Stream.of(carsService.addCar(car))
                .map(c -> new CarResponseDto(c.getId(), c.getType(), c.getColour())).
                findFirst().get();
    }

    public CarResponseDto UpdateCar(String id, CarDto carDto) {
        Car car = new Car();
        BeanUtils.copyProperties(carDto, car);

        return Stream.of(carsService.UpdateCar(id, car))
                .map(c -> new CarResponseDto(c.getId(), c.getType(), c.getColour())).
                findFirst().get();
    }

}
