package ru.sfedu.dblabs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfedu.dblabs.model.Client;

public interface ClientRepo extends JpaRepository<Client, Long> {}
