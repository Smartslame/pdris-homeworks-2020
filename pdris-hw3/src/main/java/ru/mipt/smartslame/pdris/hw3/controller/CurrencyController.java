package ru.mipt.smartslame.pdris.hw3.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mipt.smartslame.pdris.hw3.model.CurrencyStampList;
import ru.mipt.smartslame.pdris.hw3.service.CurrencyService;

@Controller
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(value = "/currency", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    CurrencyStampList getCurrencies(@RequestParam(defaultValue = "1") int n, @RequestParam(required = false) String currencyId) {
        return currencyService.getCurrencies(n, currencyId);
    }
}
