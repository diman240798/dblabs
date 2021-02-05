package ru.sfedu.dblabs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfedu.dblabs.model.FederalService;

public interface FederalServiceRepo extends JpaRepository<FederalService, Long> {}
