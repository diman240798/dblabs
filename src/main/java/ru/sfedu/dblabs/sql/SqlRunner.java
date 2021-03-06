package ru.sfedu.dblabs.sql;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Component
public class SqlRunner {

    private final EntityManager entityManager;

    public SqlRunner(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void run(String request, File file) throws IOException {
        try {
            Query nativeQuery = entityManager.createNativeQuery(request);
            long start = System.currentTimeMillis();
            List resultList = nativeQuery.getResultList();
            long end = System.currentTimeMillis();
            long time = end - start;
            Files.writeString(file.toPath(), time + ",", StandardOpenOption.APPEND);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
