package com.weathersense.api.dto;


public class City {
    private String cityName;
    private String cityState;
    private String lat;
    private String lon;
    private String temperature;
    private String windspeed;
    private String isDay;

    public City() {
    }

    public City(String cityName, String cityState, String lat, String lon, String temperature, String windspeed, String isDay) {
        this.cityName = cityName;
        this.cityState = cityState;
        this.lat = lat;
        this.lon = lon;
        this.temperature = temperature;
        this.windspeed = windspeed;
        this.isDay = isDay;
    }

    public String getIsDay() {
        return isDay;
    }

    public void setIsDay(String isDay) {
        this.isDay = isDay;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityState() {
        return cityState;
    }

    public void setCityState(String cityState) {
        this.cityState = cityState;
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

    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }
}