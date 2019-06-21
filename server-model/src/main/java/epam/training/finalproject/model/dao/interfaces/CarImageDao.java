package epam.training.finalproject.model.dao.interfaces;

import epam.training.finalproject.model.domain.entity.CarImage;

import java.util.List;

public interface CarImageDao extends PersistentDao<Long, CarImage> {

    List<CarImage> findCarImageByCarId(long carId);
}
