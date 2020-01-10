package epam.training.finalproject.model.domain.entity;

import java.util.List;

public class Car extends AbstractEntity {

    private List<Order> orders;
    private boolean available;
    private String registrationNumber;
    private Long carProfileId;

    public Long getCarProfileId() {
        return carProfileId;
    }

    public void setCarProfileId(Long carProfileId) {
        this.carProfileId = carProfileId;
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
