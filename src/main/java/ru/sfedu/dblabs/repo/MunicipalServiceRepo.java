package ru.sfedu.dblabs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfedu.dblabs.model.MunicipalService;

public 	interface MunicipalServiceRepo extends JpaRepository<MunicipalService, Long> {}
