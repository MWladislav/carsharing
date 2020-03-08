package epam.training.finalproject.model.service.interfaces;

import epam.training.finalproject.model.domain.dto.CarDto;

import java.util.List;

public interface CarService extends AbstractEntityService<Long, CarDto> {

    CarDto findCarByRegistrationNumber(String number);

    List<CarDto> getCarsByAvailable(boolean available);

    Long updateCarAvailable(CarDto car);
}
