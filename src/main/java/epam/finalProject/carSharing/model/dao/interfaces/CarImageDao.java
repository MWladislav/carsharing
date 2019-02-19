package epam.finalProject.carSharing.model.dao.interfaces;

import epam.finalProject.carSharing.model.domain.entity.CarImage;

import java.util.List;

public interface CarImageDao extends PersistentDao<Long, CarImage> {

    List<CarImage> findCarImageByCarId(long carId);
}
