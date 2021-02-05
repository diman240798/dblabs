package ru.sfedu.dblabs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfedu.dblabs.model.Request;

public interface RequestsRepo extends JpaRepository<Request, Long> {}