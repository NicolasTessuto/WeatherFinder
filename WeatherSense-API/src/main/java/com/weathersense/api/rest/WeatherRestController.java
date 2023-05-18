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
@RequestMapping("/api")
public class WeatherRestController {
    private String geocodeUrl = "https://geocode.maps.co/search?q={q}";
    private String openMeteoUrl = "https://api.open-meteo.com/v1/forecast?latitude={lat}&longitude={lon}&current_weather=True";
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/weather")
    public ResponseEntity<City> geneteWeather(@RequestParam("name") String name, @RequestParam("state") String state) throws JsonProcessingException {
        Map<String, String> paramsQ = new HashMap<>();
        paramsQ.put("q", "{" + name + "," + state + "}");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> geoCodeResponse = restTemplate.getForEntity(geocodeUrl, String.class, paramsQ);

        JsonNode jsonNode = objectMapper.readTree(geoCodeResponse.getBody());
        String lat = jsonNode.get(0).get("lat").asText();
        String lon = jsonNode.get(0).get("lon").asText();

        Map<String, String> paramsLatLon = new HashMap<>();
        paramsLatLon.put("lat", lat);
        paramsLatLon.put("lon", lon);
        ResponseEntity<String> meteoResponse = restTemplate.getForEntity(openMeteoUrl, String.class, paramsLatLon);

        jsonNode = objectMapper.readTree(meteoResponse.getBody());
        String temperature = jsonNode.get("current_weather").get("temperature").asText();

        City city = new City(name, state, lat, lon, temperature);

        return ResponseEntity.ok(city);
    }

}
