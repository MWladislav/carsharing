package epam.training.finalproject.model.service.conventer;

import epam.training.finalproject.model.domain.dto.AbstractEntityDto;
import epam.training.finalproject.model.domain.dto.CarDto;
import epam.training.finalproject.model.domain.dto.CarProfileDto;
import epam.training.finalproject.model.domain.dto.UserDto;
import epam.training.finalproject.model.domain.entity.AbstractEntity;
import epam.training.finalproject.model.domain.entity.Car;
import epam.training.finalproject.model.domain.entity.CarProfile;
import epam.training.finalproject.model.domain.entity.User;
import org.hibernate.Hibernate;

public class EntityConventer {

    public static AbstractEntityDto convertToDto(AbstractEntity entity) {
        if (entity instanceof User) {
            return convertToUserDto((User) entity);
        } else if (entity instanceof CarProfile) {
            return convertToCarProfileDto((CarProfile) entity);
        } else if (entity instanceof Car) {
            return convertToCarDto((Car) entity);
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
            carProfileDto.setImages(carProfile.getImages());
        }
        if (carProfile.getMainImage() != null) {
            carProfileDto.setMainImage(carProfile.getMainImage());
        }
        if (Hibernate.isInitialized(carProfile.getCars()) && carProfile.getCars() != null) {
            carProfileDto.setCars(carProfile.getCars());
        }
        return carProfileDto;
    }

    private static CarDto convertToCarDto(Car car) {
        CarDto carDto = new CarDto();
        carDto.setRegistrationNumber(car.getRegistrationNumber());
        carDto.setAvailable(car.isAvailable());
        if (car.getCarProfile() != null){
            carDto.setCarProfile(car.getCarProfile());
        }
        if (car.getOrders() != null){
            carDto.setOrders(car.getOrders());
        }
        return carDto;
    }
}
