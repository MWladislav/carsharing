package epam.training.finalproject.model.service.interfaces;

import epam.training.finalproject.model.domain.entity.CarProfile;
import epam.training.finalproject.model.domain.entity.enums.CarBodyType;
import epam.training.finalproject.model.domain.entity.enums.CarEngineType;

public interface CarProfileService extends AbstractEntityService<Long, CarProfile> {

    CarProfile findByManufacturer(String manufacturer);

    CarProfile findByModel(String model);

    CarProfile findByBodyType(CarBodyType bodyType);

    CarProfile findByEngineType(CarEngineType engineType);
}
