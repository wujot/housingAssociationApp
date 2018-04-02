package spring.mvc.housing.association.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class HousingAssociation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private String street;
    private int number;
    private int numberOfFlats;

    @OneToMany(mappedBy = "housingAssociation")
    private List<Flat> flats;

    public HousingAssociation() {
    }

    public HousingAssociation(String name, String street, int number, int numberOfFlats) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.number = number;
        this.numberOfFlats = numberOfFlats;
        this.flats = flats;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfFlats() {
        return numberOfFlats;
    }

    public void setNumberOfFlats(int numberOfFlats) {
        this.numberOfFlats = numberOfFlats;
    }

    public List<Flat> getFlats() {
        return flats;
    }

    public void setFlats(List<Flat> flats) {
        this.flats = flats;
    }

    @Override
    public String toString() {
        return "HousingAssociation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", numberOfFlats=" + numberOfFlats +
                '}';
    }
}