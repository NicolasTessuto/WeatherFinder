package com.weathersense.api.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weathersense.api.dto.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class WeatherRestController {
    private final String GeocodeUrl = "https://geocode.maps.co/search?q={q}";
    private final String OpenMeteoUrl = "https://api.open-meteo.com/v1/forecast?latitude={lat}&longitude={lon}&current_weather=True";
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/weather")
    public ResponseEntity<City> generateWeather(@RequestParam("name") String name, @RequestParam("state") String state) throws JsonProcessingException {

        return ResponseEntity.ok(generateGeoCodeResponse(name, state));
    }

    public City generateGeoCodeResponse(String cityName, String cityState) throws JsonProcessingException {

        Map<String, String> paramsQ = new HashMap<>();
        paramsQ.put("q", "{" + cityName + "," + cityState + "}");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> geoCodeResponse = restTemplate.getForEntity(GeocodeUrl, String.class, paramsQ);

        return generateMeteoResponse(cityName, cityState, geoCodeResponse, restTemplate);
    }

    public City generateMeteoResponse(String cityName, String cityState, ResponseEntity<String> geoCodeResponse, RestTemplate restTemplate) throws JsonProcessingException {

        JsonNode jsonNode = objectMapper.readTree(geoCodeResponse.getBody());
        String lat = jsonNode.get(0).get("lat").asText();
        String lon = jsonNode.get(0).get("lon").asText();
        Map<String, String> paramsLatLon = new HashMap<>();
        paramsLatLon.put("lat", lat);
        paramsLatLon.put("lon", lon);
        ResponseEntity<String> meteoResponse = restTemplate.getForEntity(OpenMeteoUrl, String.class, paramsLatLon);
        jsonNode = objectMapper.readTree(meteoResponse.getBody());
        String temperature = jsonNode.get("current_weather").get("temperature").asText();
        String windSpeed = jsonNode.get("current_weather").get("windspeed").asText();
        String isDay = jsonNode.get("current_weather").get("is_day").asText();

        City city = new City(cityName, cityState, lat, lon, temperature, windSpeed, isDay);
        return city;

    }
    
}
