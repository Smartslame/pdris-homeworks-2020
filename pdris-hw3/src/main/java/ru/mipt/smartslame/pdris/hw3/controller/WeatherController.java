package ru.mipt.smartslame.pdris.hw3.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mipt.smartslame.pdris.hw3.entity.WeatherStats;
import ru.mipt.smartslame.pdris.hw3.service.WeatherService;

import javax.validation.constraints.Min;

@Controller
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    WeatherStats getWeather(@RequestParam(defaultValue = "1") @Min(1) int n) {
        return weatherService.getWeather(n);
    }
}
