package epam.training.finalproject.model.dao.interfaces;

import epam.training.finalproject.model.domain.entity.CarProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CarProfileRepository extends JpaRepository<CarProfile, Long>, JpaSpecificationExecutor<CarProfile> {
}
