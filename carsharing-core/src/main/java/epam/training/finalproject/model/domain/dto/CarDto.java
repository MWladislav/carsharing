package epam.training.finalproject.model.domain.dto;

import epam.training.finalproject.model.domain.entity.CarProfile;
import epam.training.finalproject.model.domain.entity.Order;

import java.util.List;

public class CarDto extends AbstractEntityDto{

    private List<Order> orders;
    private boolean available;
    private String registrationNumber;
    private CarProfile carProfile;

    public CarDto() {
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public CarProfile getCarProfile() {
        return carProfile;
    }

    public void setCarProfile(CarProfile carProfile) {
        this.carProfile = carProfile;
    }
}
