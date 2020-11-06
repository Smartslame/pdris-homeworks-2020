package ru.mipt.smartslame.pdris.hw3.service;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.mipt.smartslame.pdris.hw3.entity.WeatherDayStats;
import ru.mipt.smartslame.pdris.hw3.entity.WeatherStats;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WeatherService {
    private final RestTemplate restTemplate;
    private final String apiKey = "e197d371f7594d3fb07142422202810";
    private final String url = "http://api.weatherapi.com/v1/history.json";
    private final String city = "Moscow";
    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String getRequestUri(int daysCount) {
        daysCount--;
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam("key", apiKey)
                .queryParam("q", city)
                .queryParam(
                        "dt",
                        currentDate.minusDays(daysCount).format(formatter)
                )
                .queryParam(
                        "end_dt",
                        currentDate.format(formatter)
                )
                .toUriString();
    }

    public WeatherStats getWeather(int daysCount) {
        return restTemplate.getForObject(getRequestUri(daysCount), WeatherStats.class);

    }


}
