package ru.sfedu.dblabs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sfedu.dblabs.model.Emploee;

public interface EmploeeRepo extends JpaRepository<Emploee, Long> {}
