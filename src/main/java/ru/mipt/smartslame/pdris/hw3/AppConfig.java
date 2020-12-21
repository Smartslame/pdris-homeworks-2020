package ru.mipt.smartslame.pdris.hw3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.mipt.smartslame.pdris.hw3.repository.CurrencyRepository;
import ru.mipt.smartslame.pdris.hw3.repository.WeatherRepository;
import ru.mipt.smartslame.pdris.hw3.service.CurrencyService;
import ru.mipt.smartslame.pdris.hw3.service.PredictionService;
import ru.mipt.smartslame.pdris.hw3.service.WeatherService;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CurrencyService currencyService(
            CurrencyRepository currencyRepository,
            RestTemplate restTemplate,
            @Value("${currency.api.url}") String apiUrl,
            @Value("${currency.default.id}") String defaultCurrencyId) {
        return new CurrencyService(currencyRepository, restTemplate, apiUrl, defaultCurrencyId);
    }

    @Bean
    public WeatherService weatherService(
            WeatherRepository weatherRepository,
            RestTemplate restTemplate,
            @Value("${weather.api.key}") String apiKey,
            @Value("${weather.api.url}") String apiUrl,
            @Value("${weather.default.city}") String defaultCity) {
        return new WeatherService(weatherRepository, restTemplate, apiKey, apiUrl, defaultCity);
    }

    @Bean
    public PredictionService predictionService(
            CurrencyService currencyService,
            WeatherService weatherService,
            @Value("${weather.api.days.limit}") int daysLimit) {
        return new PredictionService(currencyService, weatherService, daysLimit);
    }
}
