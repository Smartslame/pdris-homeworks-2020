package ru.mipt.smartslame.pdris.hw3.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherStats {
    private String city;
    private List<WeatherDayStats> weatherDayStats;

    public String getCity() {
        return city;
    }

    public List<WeatherDayStats> getWeatherDayStats() {
        return weatherDayStats;
    }

    @JsonProperty("location")
    private void setCity(Map<String, Object> location) {
        this.city = (String) location.get("name");
    }

    @JsonProperty("forecast")
    private void setWeatherDayStats(Map<String, Object> forecast) {
        ArrayList<Map<String, Object>> daylyForecasts = (ArrayList<Map<String, Object>>) forecast.get("forecastday");
        this.weatherDayStats = daylyForecasts.stream()
                .map(daylyForecast -> new WeatherDayStats(daylyForecast))
                .collect(Collectors.toList());
    }

}
