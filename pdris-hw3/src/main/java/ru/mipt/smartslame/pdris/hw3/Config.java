package ru.mipt.smartslame.pdris.hw3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.mipt.smartslame.pdris.hw3.service.CurrencyService;
import ru.mipt.smartslame.pdris.hw3.service.WeatherService;

@Configuration
public class Config {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CurrencyService currencyService(RestTemplate restTemplate) {
        return new CurrencyService(restTemplate);
    }

    @Bean
    public WeatherService weatherService(RestTemplate restTemplate) {
        return new WeatherService(restTemplate);
    }
}
