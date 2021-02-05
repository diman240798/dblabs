package ru.sfedu.dblabs.csvModel;

public class CsvCity {
    private String city_id;
    private String country_id;
    private String region_id;
    private String name;


    public CsvCity() {
    }

    public CsvCity(String city_id, String country_id, String region_id, String name) {
        this.city_id = city_id;
        this.country_id = country_id;
        this.region_id = region_id;
        this.name = name;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CsvCity{" +
                "city_id='" + city_id + '\'' +
                ", country_id='" + country_id + '\'' +
                ", region_id='" + region_id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
