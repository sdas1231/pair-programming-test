package com.nwboxed.simplespring.service;

import com.nwboxed.simplespring.dto.CarResponseDto;
import com.nwboxed.simplespring.model.Car;
import com.nwboxed.simplespring.repository.CarsRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarsService {

    private CarsRepository carsRepository;

    @Override
    @Caching(evict = {
            @CacheEvict(value = "cars", allEntries = true),
            @CacheEvict(value = "car", allEntries = true)
    })
    public Car addCar(Car car) {
        return carsRepository.save(car);
    }

    @Override
    @Cacheable("cars")
    public List<CarResponseDto> getCars() {
        return getCarResponse(loadCars());
    }

    @Override
    @Cacheable(value = "car", key = "#id")
    public CarResponseDto getCar(String id) {
        List<Car> cars = loadCars();
        return cars.stream().filter(car -> id.equalsIgnoreCase(car.getId())).findFirst().
                map(c -> new CarResponseDto(c.getId(), c.getType(), c.getColour())).
                orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "entity not found"
                ));
    }

    @Override
    @CacheEvict(value = "car", key = "#id")
    public void deleteCar(String id) {
        Car car = loadCars().stream().filter(c -> id.equalsIgnoreCase(c.getId())).
                findFirst().orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "entity not found"
                ));
        carsRepository.delete(car);
    }

    @Override
    @CachePut(value = "car", key = "#id")
    @CacheEvict(value = "cars", allEntries = true)
    public Car UpdateCar(String id, Car car) {
        List<Car> cars = loadCars();
        cars.stream().filter(c -> id.equalsIgnoreCase(c.getId())).findFirst().
                orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "entity not found"
                ));

        car.setId(id);
        return carsRepository.save(car);
    }

    @Override
    @CacheEvict(value = "cars", allEntries = true)
    public List<CarResponseDto> getCarByField(@NonNull final String colour) {
        return getCarResponse(carsRepository.findByColour(colour));
        //return null;
    }

    private List<Car> loadCars() {
        return carsRepository.findAll();
    }

    private List<CarResponseDto> getCarResponse(List<Car> cars) {
        return cars.stream().map(car -> new CarResponseDto(car.getId(),
                car.getType(), car.getColour())).collect(
                Collectors.collectingAndThen(Collectors.toList(), result -> {
                    if (result.isEmpty()) throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "entity not found"
                    );
                    return result;
                }));
    }
}
