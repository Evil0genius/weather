package com.example.weather.model;


public class Weather {
    private String cityQuery;
    private String name;
    private Integer tempCelsius;


    public Weather(String cityQuery) {
        this.cityQuery = cityQuery;
    }

    public Weather() {

    }

    public Weather(String name, Integer tempCelsius) {
        this.name = name;
        this.tempCelsius = tempCelsius;
    }

    public String getCityQuery() {
        return cityQuery;
    }

    public void setCityQuery(String cityQuery) {
        this.cityQuery = cityQuery;
    }

    public String getAPI() {
        String API = "897178061a91dabf006428405bc65968";
        return API;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTempCelsius() {
        return tempCelsius;
    }

    public void setTempCelsius(Integer tempCelsius) {
        this.tempCelsius = tempCelsius;
    }
}
