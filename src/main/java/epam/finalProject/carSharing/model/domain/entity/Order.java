package epam.finalProject.carSharing.model.domain.entity;

import epam.finalProject.carSharing.model.domain.entity.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class Order extends Persistent {

    private LocalDateTime confirmationDate;
    private LocalDateTime paymentDate;
    //order expiration date in minutes
    private int orderDuration;
    private OrderStatus status;
    private int price;
    private long carId;
    private List<OrderAdditionalInfo> orderAdditionalInfo;
    private long userId;

    public Order() {}

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public List<OrderAdditionalInfo> getOrderAdditionalInfo() {
        return orderAdditionalInfo;
    }

    public void setOrderAdditionalInfo(List<OrderAdditionalInfo> orderAdditionalInfo) {
        this.orderAdditionalInfo = orderAdditionalInfo;
    }

    public LocalDateTime getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(LocalDateTime confirmationDate) {
        this.confirmationDate = confirmationDate;
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

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }
}
