package com.nwboxed.simplespring.service;

import com.nwboxed.simplespring.client.CarApiClient;
import com.nwboxed.simplespring.dto.CarCountResponseDto;
import com.nwboxed.simplespring.dto.CarResponseDto;
import com.nwboxed.simplespring.exception.CarException;
import com.nwboxed.simplespring.exception.ErrorCodes;
import com.nwboxed.simplespring.model.Car;
import com.nwboxed.simplespring.repository.CarsRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
public class CarServiceImpl implements CarsService {

    private CarsRepository carsRepository;
    private CarApiClient carApiClient;

    @Override
    @Caching(evict = {
            @CacheEvict(value = "cars", allEntries = true),
            @CacheEvict(value = "car", allEntries = true)
    })
    public Car addCar(Car car) {
        log.info("Adding CAR to repository");
        if(carsRepository.existsById(car.getId()))
            throw new CarException(
                    ErrorCodes.DUPLICATE_ENTITY.getCode(),
                    ErrorCodes.DUPLICATE_ENTITY.getMessage());
        return carsRepository.save(car);
    }

    @Override
    @Cacheable("cars")
    public List<CarResponseDto> getCars() throws CarException {
        log.info("Getting all CARs from repository");
        return getCarResponse(loadCars());
    }

    @Override
    @Cacheable(value = "car", key = "#id")
    public CarResponseDto getCar(String id) {
        log.info("Getting a CAR from repository by Id");
        List<Car> cars = loadCars();
        return cars.stream().filter(car -> id.equalsIgnoreCase(car.getId())).findFirst().
                map(c -> new CarResponseDto(c.getId(), c.getType(), c.getColour())).
                orElseThrow(() -> new CarException(
                        ErrorCodes.ENTITY_NOT_FOUND.getCode(), ErrorCodes.ENTITY_NOT_FOUND.getMessage()
                ));
    }

    @Override
    @CacheEvict(value = "car", key = "#id")
    public void deleteCar(String id) {
        log.info("Deleting a CAR from repository by Id");
        Car car = loadCars().stream().filter(c -> id.equalsIgnoreCase(c.getId())).
                findFirst().orElseThrow(() -> new CarException(
                        ErrorCodes.ENTITY_NOT_FOUND.getCode(), ErrorCodes.ENTITY_NOT_FOUND.getMessage()
                ));
        carsRepository.delete(car);
    }

    @Override
    @CachePut(value = "car", key = "#id")
    @CacheEvict(value = "cars", allEntries = true)
    public Car UpdateCar(String id, Car car) {
        log.info("Updating a CAR in repository by Id");
        List<Car> cars = loadCars();
        cars.stream().filter(c -> id.equalsIgnoreCase(c.getId())).findFirst().
                orElseThrow(() -> new CarException(
                        ErrorCodes.ENTITY_NOT_FOUND.getCode(), ErrorCodes.ENTITY_NOT_FOUND.getMessage()
                ));

        car.setId(id);
        return carsRepository.save(car);
    }

    @Override
    @CacheEvict(value = "cars", allEntries = true)
    public List<CarResponseDto> getCarByField(@NonNull final String colour) {
        log.info("Getting all CARs from repository by Colour");
        return getCarResponse(carsRepository.findByColour(colour));
    }

    @Override
    public CarCountResponseDto getCarCount(String colour, String type) {
        log.info("Getting Car Counts from repository by Colour and/or Type");
        var count = carApiClient.getAllCars().stream()
                .filter(c -> Objects.isNull(colour) || c.colour().equalsIgnoreCase(colour))
                .filter(c -> Objects.isNull(type) || c.type().equalsIgnoreCase(type)).count();

        return new CarCountResponseDto("Total number of cars with" + (Objects.isNull(colour) ? "" : " Colour " + colour) +
                (Objects.isNull(type) ? "" : " Type " + type) + " is  " + count);
    }

    private List<Car> loadCars() {
        return carsRepository.findAll();
    }

    private List<CarResponseDto> getCarResponse(List<Car> cars) {
        return cars.stream().map(car -> new CarResponseDto(car.getId(),
                car.getType(), car.getColour())).collect(
                Collectors.collectingAndThen(Collectors.toList(), result -> {
                    if (result.isEmpty()) throw new CarException(
                            ErrorCodes.ENTITY_NOT_FOUND.getCode(), ErrorCodes.ENTITY_NOT_FOUND.getMessage()
                    );
                    return result;
                }));
    }
}
