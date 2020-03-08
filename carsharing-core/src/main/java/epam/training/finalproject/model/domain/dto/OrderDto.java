package epam.training.finalproject.model.domain.dto;

import epam.training.finalproject.model.domain.entity.Car;
import epam.training.finalproject.model.domain.entity.OrderAdditionalInfo;
import epam.training.finalproject.model.domain.entity.User;
import epam.training.finalproject.model.domain.entity.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto extends AbstractEntityDto {

    private LocalDateTime confirmationDate;
    private LocalDateTime paymentDate;
    private int orderDuration;
    private OrderStatus status;
    private int price;
    private Car car;
    private List<OrderAdditionalInfo> orderAdditionalInfo;
    private User user;

    public OrderDto() {
    }

    public LocalDateTime getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(LocalDateTime confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getOrderDuration() {
        return orderDuration;
    }

    public void setOrderDuration(int orderDuration) {
        this.orderDuration = orderDuration;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<OrderAdditionalInfo> getOrderAdditionalInfo() {
        return orderAdditionalInfo;
    }

    public void setOrderAdditionalInfo(List<OrderAdditionalInfo> orderAdditionalInfo) {
        this.orderAdditionalInfo = orderAdditionalInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
