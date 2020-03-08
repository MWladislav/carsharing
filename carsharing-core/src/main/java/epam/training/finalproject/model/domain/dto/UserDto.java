package epam.training.finalproject.model.domain.dto;

import epam.training.finalproject.model.domain.entity.Order;
import epam.training.finalproject.model.domain.entity.PassportData;

import java.util.List;

public class UserDto extends AbstractEntityDto {

    private String username;
    private String email;
    private boolean active;
    private PassportData passportData;
    private List<Order> orders;

    public UserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public PassportData getPassportData() {
        return passportData;
    }

    public void setPassportData(PassportData passportData) {
        this.passportData = passportData;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
