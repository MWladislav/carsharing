package epam.training.finalproject.model.dao.interfaces;

import epam.training.finalproject.model.domain.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarDao extends PersistentDao<Long, Car>{

    Optional<Car> findByRegistrationNumber(String registrationNumber);

    Long updateCarAvailable(Car carProfile);

    List<Car> getCarsByAvailable(boolean available);
}
