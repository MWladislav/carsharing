package epam.training.finalproject.model.service.interfaces;

import epam.training.finalproject.model.domain.entity.CarProfile;

import java.util.List;

public interface CarService extends PersistentService<Long, CarProfile> {

    CarProfile findByManufacturer(String manufacturer);

    Long updateCarAvailable(CarProfile carProfile);

    List<CarProfile> getCarsByAvailable(boolean available);

}
