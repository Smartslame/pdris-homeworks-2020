package ru.mipt.smartslame.pdris.hw3.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mipt.smartslame.pdris.hw3.entity.CurrencyStamp;
import ru.mipt.smartslame.pdris.hw3.entity.WeatherStamp;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class WeatherRepositoryTest {
    @Autowired
    WeatherRepository weatherRepository;

    @Test
    public void weatherRepositoryTest() {
        WeatherStamp weatherStamp = new WeatherStamp("test", LocalDate.now(), 1, 2, 3, 4, 5);
        weatherRepository.save(weatherStamp);
        List<WeatherStamp> weatherStamps = weatherRepository.findAllByCityAndDateBetween("test", LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));
        assertNotNull(weatherStamps);
        assertEquals(weatherStamp.getCity(), weatherStamps.get(0).getCity());
        assertEquals(weatherStamp.getDate(), weatherStamps.get(0).getDate());
        assertEquals(weatherStamp.getAvgTemp(), weatherStamps.get(0).getAvgTemp());

    }
}