package epam.training.finalproject.model.dao.interfaces;

import epam.training.finalproject.model.domain.entity.Car;

import java.util.List;

public interface CarDao extends PersistentDao<Long,Car> {

    Long updateCarAvailable(Car car);

    List<Car> getCarsByAvailable(boolean available);
}
