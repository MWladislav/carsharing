package epam.training.finalproject.model.domain.dto;

import epam.training.finalproject.model.domain.entity.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto extends AbstractEntityDto {

    private LocalDateTime confirmationDate;
    private LocalDateTime paymentDate;
    private int orderDuration;
    private OrderStatus status;
    private int price;
    private CarDto carDto;
    private List<OrderAdditionalInfoDto> orderAdditionalInfoDto;
    private UserDto userDto;

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

    public CarDto getCarDto() {
        return carDto;
    }

    public void setCarDto(CarDto carDto) {
        this.carDto = carDto;
    }

    public List<OrderAdditionalInfoDto> getOrderAdditionalInfoDto() {
        return orderAdditionalInfoDto;
    }

    public void setOrderAdditionalInfoDto(List<OrderAdditionalInfoDto> orderAdditionalInfoDto) {
        this.orderAdditionalInfoDto = orderAdditionalInfoDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
