package com.nwboxed.simplespring.helper;

import com.nwboxed.simplespring.dto.CarDto;
import com.nwboxed.simplespring.dto.CarResponseDto;
import com.nwboxed.simplespring.model.Car;
import com.nwboxed.simplespring.service.CarsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@DisplayName("Test CarServiceHelper Class")
@ExtendWith(MockitoExtension.class)
public class CarServiceHelperTest {

    @Mock
    private CarsService carsService;

    @InjectMocks
    private CarServiceHelper carServiceHelper;

    @Test
    void TC01_itShouldTestAddCarMethod() {
        //Given
        CarDto carTobeSaved = CarDto.builder().
                type("BMW").
                colour("red").build();

        Car car = Car.builder().
                id("441df2d3-198d-46fd-be4b-1f51ca532425").
                type("BMW").
                colour("red").build();

        CarResponseDto response = CarResponseDto.builder().
                id("441df2d3-198d-46fd-be4b-1f51ca532425").
                type("BMW").
                colour("red").build();

        //Stub
        doReturn(car).when(carsService).addCar(any());

        //When
        CarResponseDto carResponse;
        carResponse = carServiceHelper.addCar(carTobeSaved);

        //Then
        assertAll(
                () -> assertThat(carResponse).isEqualTo(response)
        );
    }
}
