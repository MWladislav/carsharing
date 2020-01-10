package epam.training.finalproject.model.dao.interfaces;

import epam.training.finalproject.model.domain.entity.CarProfile;
import epam.training.finalproject.model.domain.entity.enums.CarBodyType;
import epam.training.finalproject.model.domain.entity.enums.CarEngineType;

import java.util.List;

public interface CarProfileDao extends AbstractEntityDao<Long, CarProfile> {

    List<CarProfile> findByManufacturer(String manufacturer);

    List<CarProfile> findByModel(String model);

    List<CarProfile> findByBodyType(CarBodyType bodyType);

    List<CarProfile> findByEngineType(CarEngineType engineType);

    List<CarProfile> findByYearOfIssue(int yearOfIssue);
}
