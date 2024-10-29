package com.nwboxed.simplespring.client;

import com.nwboxed.simplespring.dto.CarResponseDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

@Service
@Profile({"dev","part2"})
public class DummyApiClient implements CarApiClient {

    @Override
    public List<CarResponseDto> getAllCars() {
        return asList(
                new CarResponseDto("1", "Hatchback", "RED"),
                new CarResponseDto("2", "Sedan", "BLACK"),
                new CarResponseDto("3", "4x4", "BLACK"),
                new CarResponseDto("4", "4x4", "RED"),
                new CarResponseDto("5", "SUV", "GREEN"));
    }

}
