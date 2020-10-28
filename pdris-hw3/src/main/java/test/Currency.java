package test;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;
import java.util.Date;

@JacksonXmlRootElement(localName = "Record")
public class Currency implements Serializable {
    @JacksonXmlProperty(localName = "Id", isAttribute = true)
    private String currencyId;
    @JacksonXmlProperty(localName = "Date", isAttribute = true)
    @JsonDeserialize(using = DateDeserializer.class)
    private Date date;
    @JacksonXmlProperty(localName = "Value")
    @JsonDeserialize(using = DoubleDeserializer.class)
    private double cost;

    public String getCurrencyId() {
        return currencyId;
    }

    public Date getDate() {
        return date;
    }

    public double getCost() {
        return cost;
    }


}
