package ru.sfedu.dblabs.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class City {
    @Id
    private int id;

    private String name;
    private long foundDate;

    public City() {
    }

    public City(int id, String name, long foundDate) {
        this.id = id;
        this.name = name;
        this.foundDate = foundDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(long foundDate) {
        this.foundDate = foundDate;
    }
}
