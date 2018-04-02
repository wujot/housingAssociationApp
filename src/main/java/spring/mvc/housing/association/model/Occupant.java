package spring.mvc.housing.association.model;

import javax.persistence.*;

@Entity
public class Occupant {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String gender;

    @ManyToOne
    private Flat flat;

    public Occupant() {}

    public Occupant(String name, String surname, String gender) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.flat = flat;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    @Override
    public String toString() {
        return "Occupant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
