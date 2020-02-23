package epam.training.finalproject.model.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
public class Address extends AbstractEntity {
    @Column(name = "country", length = 30, nullable = false)
    private String country;
    @Column(name = "city", length = 30, nullable = false)
    private String city;
    @Column(name = "street", length = 30, nullable = false)
    private String street;
    @Column(name = "house_number", nullable = false)
    private int houseNumber;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }
}
