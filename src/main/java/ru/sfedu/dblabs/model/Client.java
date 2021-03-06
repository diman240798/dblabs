package ru.sfedu.dblabs.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Client {
    @Id
    private long id;

    private int age;
    private long cityId;
    private Date birthDate;
    private String passportNumber;
    private String passportSereis;
    private String name;
    private boolean hasConvictions;

    public Client() {}

    public Client(long id, int age, long cityId, Date birthDate, String passportNumber, String passportSereis, String name, boolean hasConvictions) {
        this.id = id;
        this.age = age;
        this.cityId = cityId;
        this.birthDate = birthDate;
        this.passportNumber = passportNumber;
        this.passportSereis = passportSereis;
        this.name = name;
        this.hasConvictions = hasConvictions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportSereis() {
        return passportSereis;
    }

    public void setPassportSereis(String passportSereis) {
        this.passportSereis = passportSereis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasConvictions() {
        return hasConvictions;
    }

    public void setHasConvictions(boolean hasConvictions) {
        this.hasConvictions = hasConvictions;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", age=" + age +
                ", cityId=" + cityId +
                ", birthDate=" + birthDate.toString() +
                ", passportNumber='" + passportNumber + '\'' +
                ", passportSereis='" + passportSereis + '\'' +
                ", name='" + name + '\'' +
                ", hasConvictions=" + hasConvictions +
                '}';
    }
}
