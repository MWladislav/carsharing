package epam.training.finalproject.model.dao.interfaces;

import epam.training.finalproject.model.domain.entity.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CarImageRepository extends JpaRepository<CarImage, Long>, JpaSpecificationExecutor<CarImage> {
}
