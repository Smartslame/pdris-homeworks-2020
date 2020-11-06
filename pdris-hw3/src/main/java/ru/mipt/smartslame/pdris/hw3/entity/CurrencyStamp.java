package ru.mipt.smartslame.pdris.hw3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import ru.mipt.smartslame.pdris.hw3.jackson.custom.DateDeserializer;
import ru.mipt.smartslame.pdris.hw3.jackson.custom.DoubleDeserializer;

import java.time.LocalDate;

@JacksonXmlRootElement(localName = "Record")
public class CurrencyStamp {
    @JacksonXmlProperty(localName = "Id", isAttribute = true)
    private String id;
    @JacksonXmlProperty(localName = "Date", isAttribute = true)
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDate date;
    @JacksonXmlProperty(localName = "Value")
    @JsonDeserialize(using = DoubleDeserializer.class)
    private double cost;

    @JsonIgnore
    public String getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getCost() {
        return cost;
    }
}
