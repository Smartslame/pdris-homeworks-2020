package ru.mipt.smartslame.pdris.hw3.entity;

import java.util.Map;

public class WeatherDayStats {
    private String date;
    private double minTemp;
    private double maxTemp;
    private double maxWindSpeed;
    private double avgVisibility;

    public WeatherDayStats(Map<String,Object> daylyForecast) {
        this.date = (String) daylyForecast.get("date");
        Map<String, Object> stats = (Map<String, Object>) daylyForecast.get("day");
        this.maxTemp = (double) stats.get("maxtemp_c");
        this.minTemp = (double) stats.get("mintemp_c");
        this.maxWindSpeed = (double) stats.get("maxwind_kph");
        this.avgVisibility = (double) stats.get("avgvis_km");
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public double getAvgVisibility() {
        return avgVisibility;
    }

    public String getDate() {
        return date;
    }
}
