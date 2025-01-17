package com.nwboxed.simplespring.repository;

import com.nwboxed.simplespring.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarsRepository extends JpaRepository<Car, String> {
    List<Car> findByColour(String colour);
}
