package com.WeatherSense.WeatherSenseAPI.Entity;

public class City {

    private String name;
    private long lat;
    private long lon;
    private String state;

    public City() {
    }

    public City(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public City(String name, String state, String country, long latitude, long longitude) {
        this.name = name;
        this.state = state;
        this.lat = lat;
        this.lon = lon;
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

    public long getLatitude() {
        return lat;
    }

    public void setLatitude(long lat) {
        this.lat = lat;
    }

    public long getLongitude() {
        return lon;
    }

    public void setLongitude(long lon) {
        this.lon = lon;
    }
}