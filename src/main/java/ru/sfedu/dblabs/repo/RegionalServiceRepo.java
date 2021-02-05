package ru.sfedu.dblabs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfedu.dblabs.model.RegionalService;

public interface RegionalServiceRepo extends JpaRepository<RegionalService, Long> {}