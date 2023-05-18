package com.weathersense.api.dto;


public class City {
    private String name;
    private String state;
    private String lat;
    private String lon;
    private String temperature;

    public City() {
    }

    public City(String name, String state, String lat, String lon, String temperature) {
        this.name = name;
        this.state = state;
        this.lat = lat;
        this.lon = lon;
        this.temperature = temperature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}