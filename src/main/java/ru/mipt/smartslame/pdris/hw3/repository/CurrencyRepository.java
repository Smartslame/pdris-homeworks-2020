package ru.mipt.smartslame.pdris.hw3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mipt.smartslame.pdris.hw3.entity.CurrencyStamp;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CurrencyRepository extends CrudRepository<CurrencyStamp, Long> {
    List<CurrencyStamp> findAllByCurrencyIdAndDateBetween(String currencyId, LocalDate dateStart, LocalDate dateEnd);
}
