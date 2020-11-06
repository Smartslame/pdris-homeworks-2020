package ru.mipt.smartslame.pdris.hw3.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mipt.smartslame.pdris.hw3.entity.CurrencyStamp;
import ru.mipt.smartslame.pdris.hw3.service.CurrencyService;

import javax.validation.constraints.Min;
import java.util.List;

@Controller
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(value = "/currency", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<CurrencyStamp> getCurrencies(@RequestParam(defaultValue = "1") @Min(1) int n) {
        return currencyService.getCurrencies(n);
    }
}
