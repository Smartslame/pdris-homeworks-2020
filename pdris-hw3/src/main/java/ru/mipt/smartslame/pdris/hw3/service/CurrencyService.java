package ru.mipt.smartslame.pdris.hw3.service;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.mipt.smartslame.pdris.hw3.entity.CurrencyStamp;
import ru.mipt.smartslame.pdris.hw3.entity.CurrencyStampList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CurrencyService {
    private final RestTemplate restTemplate;
    private final String url = "http://www.cbr.ru/scripts/XML_dynamic.asp";
    private final String CurrencyId = "R01235";

    public CurrencyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String getRequestUri(int daysCount) {
        daysCount--;
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return UriComponentsBuilder
                .fromHttpUrl(url)
                .queryParam("VAL_NM_RQ", CurrencyId)
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

    public List<CurrencyStamp> getCurrencies(int daysCount) {
        CurrencyStampList currencyStampList = restTemplate.getForObject(getRequestUri(daysCount), CurrencyStampList.class);

        return currencyStampList.getCurrencies();

    }

}
