package ru.sfedu.dblabs.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RegionalService {
    @Id
    private long id;

    private String name;
    private long duration;
    private boolean rejected;
    private String type;

    public RegionalService() {}

    public RegionalService(long id, String name, long duration, boolean rejected, String type) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.rejected = rejected;
        this.type = type;
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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean isRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
