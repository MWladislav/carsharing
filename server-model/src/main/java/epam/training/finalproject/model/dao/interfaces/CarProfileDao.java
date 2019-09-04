package epam.training.finalproject.model.dao.interfaces;

import epam.training.finalproject.model.domain.entity.CarProfile;
import epam.training.finalproject.model.domain.entity.enums.CarBodyType;
import epam.training.finalproject.model.domain.entity.enums.CarEngineType;

import java.util.List;
import java.util.Optional;

public interface CarProfileDao extends PersistentDao<Long, CarProfile> {

    List<CarProfile> findByManufacturer(String manufacturer);

    List<CarProfile> findByModel(String model);

    List<CarProfile> findByBodyType(CarBodyType bodyType);

    List<CarProfile> findByEngineType(CarEngineType engineType);

}
