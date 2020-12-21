package ru.mipt.smartslame.pdris.hw3.service;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import ru.mipt.smartslame.pdris.hw3.entity.CurrencyStamp;
import ru.mipt.smartslame.pdris.hw3.entity.WeatherStamp;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PredictionService {
    private final CurrencyService currencyService;
    private final WeatherService weatherService;
    private SimpleRegression regression;
    private final int daysLimit;

    public PredictionService(CurrencyService currencyService, WeatherService weatherService, int daysLimit) {
        this.currencyService = currencyService;
        this.weatherService = weatherService;
        this.daysLimit = daysLimit;
    }

    private void initRegression(int n) {
        if (Objects.nonNull(regression)) {
            return;
        }

        regression = new SimpleRegression();

        List<CurrencyStamp> currencyStamps = currencyService.getCurrencies(n).getCurrencyStamps();
        List<WeatherStamp> weatherStamps = weatherService.getWeather(n).getWeatherStamps();

        List<WeatherStamp> weatherIntersection = weatherStamps.stream()
                .filter(weather -> currencyStamps.stream().anyMatch(currency -> currency.getDate().isEqual(weather.getDate())))
                .collect(Collectors.toList());
        for (int i = 0; i < weatherIntersection.size(); i++) {
            this.regression.addData(weatherIntersection.get(i).getAvgTemp(), currencyStamps.get(i).getCost());
        }
    }

    public double getCurrencyPrediction(double avgTemp) {
        initRegression(daysLimit);
        return regression.predict(avgTemp);
    }
}


