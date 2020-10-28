package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class Config {
    @Bean
    public URI url() throws URISyntaxException {
        return new URI("http://www.cbr.ru/scripts/XML_dynamic.asp");
    }


    @Bean
    public SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("dd.MM.yyyy");
    }

    @Bean
    @Autowired
    public UriComponentsBuilder uriComponentsBuilder(URI uri, SimpleDateFormat dateFormat) {
        return UriComponentsBuilder.fromUri(uri)
                .queryParam("VAL_NM_RQ", "R01235");

    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
