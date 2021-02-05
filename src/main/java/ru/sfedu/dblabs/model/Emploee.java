package ru.sfedu.dblabs.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Emploee {
    @Id
    private long id;

    private String name;
    private int age;
    private String phoneNumber;
    private String position;
    private String department;
    private long cityId;

    public Emploee() {}

    public Emploee(long id, String name, int age, String phoneNumber, String position, String department, long cityId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.department = department;
        this.cityId = cityId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }
}
