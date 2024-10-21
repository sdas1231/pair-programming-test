package com.nwboxed.simplespring.apiclient;

import com.nwboxed.simplespring.dto.CarResponseDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class WebApiClient {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                "http://localhost:3000/cars-api/v1/cars")
                .queryParam("colour", "black");

        try {
            List<CarResponseDto> response = new RestTemplate().exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    new ParameterizedTypeReference<List<CarResponseDto>>() {
            }).getBody();

            System.out.println(response);
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            System.err.println(ex.getStatusCode() + " - " + ex.getMessage());
        }
    }
}
