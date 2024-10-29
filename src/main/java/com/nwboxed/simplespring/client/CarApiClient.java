package com.nwboxed.simplespring.client;

import com.nwboxed.simplespring.dto.CarResponseDto;

import java.util.List;

public interface CarApiClient {

    List<CarResponseDto> getAllCars();

}
