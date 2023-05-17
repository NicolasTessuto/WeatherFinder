package com.WeatherSense.WeatherSenseAPI.Rest;


import com.WeatherSense.WeatherSenseAPI.Entity.City;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class WeatherRestController {

    private String apiUrl = "https://geocode.maps.co/search?q=";

    RestTemplate restTemplate = new RestTemplate();

    public void getLatLon(City city){
        apiUrl = apiUrl + "{"+ city.getName()+ ", "+ city.getState() + "}";
        ResponseEntity<City> response = restTemplate.getForEntity(apiUrl, City.class);
        if(response.getStatusCode().is2xxSuccessful()){
            response.getBody();
        }

    }

    @GetMapping("/weather/{cidade}-{estado}")
    public void getCityFromUser(@PathVariable String cidade, @PathVariable String estado){
        City city = new City(cidade, estado);
        getLatLon(city);
    }

}
