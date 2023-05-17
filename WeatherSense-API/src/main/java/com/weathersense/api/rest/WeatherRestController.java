package com.weathersense.weathersenseapi.rest;


import com.weathersense.weathersenseapi.dto.City;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class WeatherRestController {

    private String apiUrl = "https://geocode.maps.co/search?q=";

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/weather")
    public City getCityFromUser(@RequestParam(name = "name") String cityName, @RequestParam(name = "state") String cityState){
        City city = new City(cityName, cityState);
        return city;
    }

}
