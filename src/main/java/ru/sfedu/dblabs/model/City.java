package ru.sfedu.dblabs.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class City {
    @Id
    private long id;

    private String name;
    private Date foundDate;

    public City() {
    }

    public City(long id, String name, Date foundDate) {
        this.id = id;
        this.name = name;
        this.foundDate = foundDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(Date foundDate) {
        this.foundDate = foundDate;
    }
}
