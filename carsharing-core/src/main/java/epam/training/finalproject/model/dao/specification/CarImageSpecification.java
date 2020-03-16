package epam.training.finalproject.model.dao.specification;

import epam.training.finalproject.model.domain.entity.CarImage;
import org.springframework.data.jpa.domain.Specification;

public class CarImageSpecification {

    public static Specification<CarImage> findDefaultMainImage() {
        return (r, cq, cb) -> cb.and(cb.equal(r.get("id"), 1L),cb.equal(r.get("mainImage"), true));
    }
}
