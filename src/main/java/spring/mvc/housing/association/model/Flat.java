package spring.mvc.housing.association.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Flat {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int number;
    private double areaSqrMeter;

    @ManyToOne
    HousingAssociation housingAssociation;

    @OneToMany(mappedBy = "flat")
    private List<Occupant> occupants;

    public Flat() {}

    public Flat(int number, double areaSqrMeter) {
        this.id = id;
        this.number = number;
        this.areaSqrMeter = areaSqrMeter;
        this.housingAssociation = housingAssociation;
        this.occupants = occupants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getAreaSqrMeter() {
        return areaSqrMeter;
    }

    public void setAreaSqrMeter(double areaSqrMeter) {
        this.areaSqrMeter = areaSqrMeter;
    }

    public List<Occupant> getOccupants() {
        return occupants;
    }

    public void setOccupants(List<Occupant> occupants) {
        occupants = occupants;
    }

    public HousingAssociation getHousingAssociation() {
        return housingAssociation;
    }

    public void setHousingAssociation(HousingAssociation housingAssociation) {
        this.housingAssociation = housingAssociation;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", number=" + number +
                ", areaSqrMeter=" + areaSqrMeter +
                ", Occupants=" + occupants +
                '}';
    }
}
