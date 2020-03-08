package epam.training.finalproject.model.dao.interfaces;

import epam.training.finalproject.model.domain.entity.CarProfile;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarProfileRepository extends JpaRepository<CarProfile, Long>, JpaSpecificationExecutor<CarProfile> {
    @EntityGraph(value = "CarProfile.Images", type = EntityGraph.EntityGraphType.LOAD)
    @Override
    List<CarProfile> findAll(Specification<CarProfile> specification);
}
