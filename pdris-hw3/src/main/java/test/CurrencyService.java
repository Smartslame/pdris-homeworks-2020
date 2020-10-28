package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class CurrencyService {
    private final RestTemplate restTemplate;
    private final UriComponentsBuilder builder;
    private final String url = "http://www.cbr.ru/scripts/XML_dynamic.asp?";

    @Autowired
    public CurrencyService(RestTemplate restTemplate, UriComponentsBuilder builder) {
        this.restTemplate = restTemplate;
        this.builder = builder;
    }

    public List<Currency> getCurrencies(int daysCount) {

//        CurrencyList currencyList = restTemplate.getForObject(
//                "http://www.cbr.ru/scripts/XML_dynamic.asp?date_req1=20/10/2020&date_req2=28/10/2020&VAL_NM_RQ=R01235",
//                CurrencyList.class);
        LocalDate currentDate = LocalDate.now();
        CurrencyList currencyList = restTemplate.getForObject(
                builder.replaceQueryParam(
                        "date_req1",
                        currentDate.minusDays(daysCount).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                        )
                        .replaceQueryParam(
                                "date_req2",
                                currentDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                        )
                        .toUriString(),
                CurrencyList.class
        );

        return currencyList.getCurrencies();

    }
}
