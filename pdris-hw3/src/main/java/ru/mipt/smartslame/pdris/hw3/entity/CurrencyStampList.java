package ru.mipt.smartslame.pdris.hw3.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;


@JacksonXmlRootElement(localName = "ValCurs")
public class CurrencyStampList {

    @JacksonXmlProperty(localName = "Record")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CurrencyStamp> currencies;

    public CurrencyStampList() {
        this.currencies = new ArrayList<>();
    }

    public List<CurrencyStamp> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyStamp> currencies) {
        this.currencies = currencies;
    }
}