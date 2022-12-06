package org.example.bean;

public class Nationality {
    private int id;
    private String name;
    private  String country_name;

    public Nationality(int id, String name, String country_name) {
        this.id = id;
        this.name = name;
        this.country_name = country_name;
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

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    @Override
    public String toString() {
        return "Nationality{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country_name='" + country_name + '\'' +
                '}';
    }
}
