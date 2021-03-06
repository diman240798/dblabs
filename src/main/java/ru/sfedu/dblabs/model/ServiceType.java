package ru.sfedu.dblabs.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ServiceType {
    @Id
    private long id;
    private String type;

    public ServiceType() {}

    public ServiceType(long id, String type) {
        this.id = id;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ServiceType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
