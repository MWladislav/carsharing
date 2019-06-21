package epam.training.finalproject.model.service.interfaces;

import epam.training.finalproject.model.domain.entity.Car;

import java.util.List;

public interface CarService extends PersistentService<Long, Car> {

    Car findByManufacturer(String manufacturer);

    Car getCarWithOrders(long id);

    Long updateCarAvailable(Car car);

    List<Car> getCarsByAvailable(boolean available);

}
