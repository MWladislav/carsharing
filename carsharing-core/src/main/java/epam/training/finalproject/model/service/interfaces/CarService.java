package epam.training.finalproject.model.service.interfaces;

import epam.training.finalproject.model.domain.entity.Car;

import java.util.List;

public interface CarService extends AbstractEntityService<Long, Car> {

    Car findCarByRegistrationNumber(String number);

    List<Car> getCarsByAvailable(boolean available);

    Long updateCarAvailable(Car car);
}
