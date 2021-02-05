package ru.sfedu.dblabs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfedu.dblabs.model.City;

public interface CityRepo extends JpaRepository<City, Long> {}

