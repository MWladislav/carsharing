package epam.training.finalproject.model.domain.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars",  uniqueConstraints = { @UniqueConstraint(columnNames = { "registration_number" }) })
@NamedEntityGraphs({
        @NamedEntityGraph(name = "Car.CarProfile",
                attributeNodes = @NamedAttributeNode(value = "carProfile")),
        @NamedEntityGraph(name = "Car.NoJoins")
})
public class Car extends AbstractEntity {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "car", fetch = FetchType.LAZY)
    private List<Order> orders;
    @Column(name = "available", nullable = false)
    private boolean available;
    @Column(name = "registration_number", length = 10, unique = true, nullable = false)
    private String registrationNumber;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "car_profile_id", nullable = false, updatable = false)
    private CarProfile carProfile;

    public CarProfile getCarProfile() {
        return carProfile;
    }

    public void setCarProfile(CarProfile carProfile) {
        this.carProfile = carProfile;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
