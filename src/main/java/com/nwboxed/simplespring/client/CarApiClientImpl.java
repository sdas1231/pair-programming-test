package com.nwboxed.simplespring.client;

import com.nwboxed.simplespring.dto.CarResponseDto;
import com.nwboxed.simplespring.exception.CarException;
import com.nwboxed.simplespring.exception.ErrorCodes;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@Log4j2
public class CarApiClientImpl implements CarApiClient {

    @Override
    public List<CarResponseDto> getAllCars() {
        // TODO:
        /*return asList(
                new Car("1", "Hatchback", "RED"),
                new Car("2", "Sedan", "BLACK"),
                new Car("3", "4x4", "BLACK"),
                new Car("4", "4x4", "RED"),
                new Car("5", "SUV", "GREEN"));*/

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                        "http://localhost:3000/cars-api/v1/cars");

        try {

            List<CarResponseDto> response = new RestTemplate().exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    new ParameterizedTypeReference<List<CarResponseDto>>() {
                    }).getBody();

            log.info("Fetched Cars from  3rd party provider - {}", response);
            return response;
        } catch (HttpClientErrorException | HttpServerErrorException | CarException ex) {
            log.error("Error while calling 3rd Party provider - {} - {}",
                    ex.getCause(), ex.getMessage());
            throw new CarException(ErrorCodes.INTERNAL_SERVER_ERROR.getCode(),
                    ErrorCodes.INTERNAL_SERVER_ERROR.getMessage());
        }
    }

}
