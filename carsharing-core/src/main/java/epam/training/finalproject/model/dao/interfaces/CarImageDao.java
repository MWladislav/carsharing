package epam.training.finalproject.model.dao.interfaces;

import epam.training.finalproject.model.domain.entity.CarImage;

import java.util.List;
import java.util.Optional;

public interface CarImageDao extends AbstractEntityDao<Long, CarImage> {

    List<CarImage> findCarImagesByCarProfileId(Long carProfileId);

    Optional<CarImage> getMainCarImageByCarProfileId(Long carProfileId);
}
