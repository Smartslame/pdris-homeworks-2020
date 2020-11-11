package ru.mipt.smartslame.pdris.hw3.service;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.mipt.smartslame.pdris.hw3.model.WeatherStampList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class WeatherService {
    private final RestTemplate restTemplate;
    private final String apiKey;
    private final String apiUrl;
    private final String defaultCity;

    public WeatherService(RestTemplate restTemplate, String apiKey, String apiUrl, String defaultCity) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
        this.defaultCity = defaultCity;
    }

    private String getWeatherRequestUri(int daysCount, String city) {
        if (Objects.isNull(city)) {
            city = defaultCity;
        }
        daysCount--;
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return UriComponentsBuilder
                .fromHttpUrl(apiUrl)
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

    public WeatherStampList getWeather(int daysCount, String city) {
        return restTemplate.getForObject(getWeatherRequestUri(daysCount, city), WeatherStampList.class);
    }

    public WeatherStampList getWeather(int daysCount) {
        return getWeather(daysCount, null);
    }


}
