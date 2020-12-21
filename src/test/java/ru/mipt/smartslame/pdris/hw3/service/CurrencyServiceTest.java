package ru.mipt.smartslame.pdris.hw3.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mipt.smartslame.pdris.hw3.entity.CurrencyStamp;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CurrencyServiceTest {
    @Autowired
    CurrencyService currencyService;
    @Test
    public void getDollarCurrencies() {
        List<CurrencyStamp> currencyStamps = currencyService.getCurrencies(7).getCurrencyStamps();
        assertNotNull(currencyStamps);
        CurrencyStamp currencyStamp = currencyStamps.get(0);
        assertEquals("R01235", currencyStamp.getCurrencyId());
        assertNotNull(currencyStamp.getDate());
        assertNotNull(currencyStamp.getCost());
    }

    @Test
    public void getDefaultCurrencies() {
        List<CurrencyStamp> currencyStamps = currencyService.getCurrencies(7, null).getCurrencyStamps();
        assertNotNull(currencyStamps);
        CurrencyStamp currencyStamp = currencyStamps.get(0);
        assertEquals("R01235", currencyStamp.getCurrencyId());
        assertNotNull(currencyStamp.getDate());
        assertNotNull(currencyStamp.getCost());
    }

    @Test
    public void getCustomCurrencies() {
        List<CurrencyStamp> currencyStamps = currencyService.getCurrencies(7, "R01090B").getCurrencyStamps();
        assertNotNull(currencyStamps);
        CurrencyStamp currencyStamp = currencyStamps.get(0);
        assertEquals("R01090B", currencyStamp.getCurrencyId());
        assertNotNull(currencyStamp.getDate());
        assertNotNull(currencyStamp.getCost());
    }
}