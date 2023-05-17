package com.WeatherSense.WeatherSenseAPI.DTO;

public record City(String name, String lat, String lon, String state) {

    public City(String name, String state) {
        this(name, null, null, state);
    }

}