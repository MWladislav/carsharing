package epam.training.finalproject.model.service.conventer;

import epam.training.finalproject.model.domain.dto.*;
import epam.training.finalproject.model.domain.entity.*;

import java.util.stream.Collectors;

public class EntityConventer {

    public static AbstractEntityDto convertToDto(AbstractEntity entity) {
        if (entity instanceof User) {
            return convertToUserDto((User) entity);
        } else if (entity instanceof CarProfile) {
            return convertToCarProfileDto((CarProfile) entity);
        } else if (entity instanceof Car) {
            return convertToCarDto((Car) entity);
        } else if (entity instanceof CarImage) {
            return convertToCarImageDto((CarImage) entity);
        } else if (entity instanceof Order) {
            return convertToOrderDto((Order) entity);
        } else {
            return null;
        }
    }

    private static UserDto convertToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setActive(user.isActive());
        if (user.getOrders() != null) {
            userDto.setOrders(user.getOrders());
        }
        if (user.getPassportData() != null) {
            userDto.setPassportData(user.getPassportData());
        }
        return userDto;
    }

    private static CarProfileDto convertToCarProfileDto(CarProfile carProfile) {
        CarProfileDto carProfileDto = new CarProfileDto();
        carProfileDto.setBodyType(carProfile.getBodyType());
        carProfileDto.setEngineType(carProfile.getEngineType());
        carProfileDto.setManufacturer(carProfile.getManufacturer());
        carProfileDto.setModel(carProfile.getModel());
        carProfileDto.setYearOfIssue(carProfile.getYearOfIssue());
        if (carProfile.getImages() != null) {
            carProfileDto.setImageDto(
                    carProfile.getImages().stream()
                            .map(EntityConventer::convertToCarImageDto)
                            .collect(Collectors.toList()));
        }
        if (carProfile.getMainImage() != null) {
            carProfileDto.setMainImageDto(convertToCarImageDto(carProfile.getMainImage()));
        }
        if (carProfile.getCars() != null) {
            carProfileDto.setCarDto(
                    carProfile.getCars().stream()
                            .map(EntityConventer::convertToCarDto)
                            .collect(Collectors.toList()));
        }
        return carProfileDto;
    }

    private static CarDto convertToCarDto(Car car) {
        CarDto carDto = new CarDto();
        carDto.setRegistrationNumber(car.getRegistrationNumber());
        carDto.setAvailable(car.isAvailable());
        if (car.getCarProfile() != null){
            carDto.setCarProfileDto(convertToCarProfileDto(car.getCarProfile()));
        }
        if (car.getOrders() != null){
            carDto.setOrderDto(
                    car.getOrders().stream()
                            .map(EntityConventer::convertToOrderDto)
                            .collect(Collectors.toList()));
        }
        return carDto;
    }

    private static CarImageDto convertToCarImageDto(CarImage carImage) {
        CarImageDto carImageDto = new CarImageDto();
        carImageDto.setImageUrl(carImage.getImageUrl());
        carImageDto.setMainImage(carImage.isMainImage());
        return carImageDto;
    }

    private static OrderDto convertToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setConfirmationDate(order.getConfirmationDate());
        orderDto.setPaymentDate(order.getPaymentDate());
        orderDto.setOrderDuration(order.getOrderDuration());
        orderDto.setStatus(order.getStatus());
        orderDto.setPrice(order.getPrice());

//        orderDto.setCarDto(convertToCarDto(order.getCar()));

//        orderDto.setOrderAdditionalInfoDto(order.getOrderAdditionalInfo());

//        orderDto.setUserDto(order.getUser());
        return orderDto;
    }
}
