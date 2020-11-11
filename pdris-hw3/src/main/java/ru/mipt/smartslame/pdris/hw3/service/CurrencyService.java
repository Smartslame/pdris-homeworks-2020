package ru.mipt.smartslame.pdris.hw3.service;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.mipt.smartslame.pdris.hw3.model.CurrencyStampList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CurrencyService {
    private final RestTemplate restTemplate;
    private final String apiUrl;
    private final String defaultCurrencyId;

    public CurrencyService(RestTemplate restTemplate, String apiUrl, String defaultCurrencyId) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
        this.defaultCurrencyId = defaultCurrencyId;
    }

    private String getCurrencyRequestUri(int daysCount, String currencyId) {
        if (Objects.isNull(currencyId)) {
            currencyId = defaultCurrencyId;
        }
        daysCount--;
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return UriComponentsBuilder
                .fromHttpUrl(apiUrl)
                .queryParam("VAL_NM_RQ", currencyId)
                .queryParam(
                        "date_req1",
                        currentDate.minusDays(daysCount).format(formatter)
                )
                .queryParam(
                        "date_req2",
                        currentDate.format(formatter)
                )
                .toUriString();
    }

    public CurrencyStampList getCurrencies(int daysCount, String currencyId) {
        return restTemplate.getForObject(getCurrencyRequestUri(daysCount, currencyId), CurrencyStampList.class);
    }

    public CurrencyStampList getCurrencies(int daysCount) {
        return getCurrencies(daysCount, null);
    }

}
