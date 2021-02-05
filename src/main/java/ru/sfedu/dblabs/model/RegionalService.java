package ru.sfedu.dblabs.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RegionalService {
    @Id
    private int id;

    private String name;
    private long duration;
    private String emploeePosition;
    private String type;

    public RegionalService() {}

    public RegionalService(int id, String name, long duration, String emploeePosition, String type) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.emploeePosition = emploeePosition;
        this.type = type;
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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getEmploeePosition() {
        return emploeePosition;
    }

    public void setEmploeePosition(String emploeePosition) {
        this.emploeePosition = emploeePosition;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
