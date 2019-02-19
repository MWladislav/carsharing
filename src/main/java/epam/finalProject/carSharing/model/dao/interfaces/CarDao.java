package epam.finalProject.carSharing.model.dao.interfaces;

import epam.finalProject.carSharing.model.domain.entity.Car;

import java.util.List;

public interface CarDao extends PersistentDao<Long,Car> {

    Long updateCarAvailable(Car car);

    List<Car> getCarsByAvailable(boolean available);
}
