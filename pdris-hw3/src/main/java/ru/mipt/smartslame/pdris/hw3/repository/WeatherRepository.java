package ru.mipt.smartslame.pdris.hw3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mipt.smartslame.pdris.hw3.entity.WeatherStamp;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeatherRepository extends CrudRepository<WeatherStamp, Long> {
    List<WeatherStamp> findAllByCityAndDateBetween(String city, LocalDate dateStart, LocalDate dateEnd);
}
