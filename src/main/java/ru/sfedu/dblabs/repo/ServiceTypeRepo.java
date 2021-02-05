package ru.sfedu.dblabs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfedu.dblabs.model.ServiceType;

public interface ServiceTypeRepo extends JpaRepository<ServiceType, Long> {}