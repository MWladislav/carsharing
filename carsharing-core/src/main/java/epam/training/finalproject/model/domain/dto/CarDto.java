package epam.training.finalproject.model.domain.dto;

import java.util.List;

public class CarDto extends AbstractEntityDto{

    private List<OrderDto> orderDto;
    private boolean available;
    private String registrationNumber;
    private CarProfileDto carProfileDto;

    public CarDto() {
    }

    public List<OrderDto> getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(List<OrderDto> orderDto) {
        this.orderDto = orderDto;
    }

    public CarProfileDto getCarProfileDto() {
        return carProfileDto;
    }

    public void setCarProfileDto(CarProfileDto carProfileDto) {
        this.carProfileDto = carProfileDto;
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
}
