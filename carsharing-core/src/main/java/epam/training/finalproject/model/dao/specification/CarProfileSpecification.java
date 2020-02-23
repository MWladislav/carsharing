package epam.training.finalproject.model.dao.specification;

import epam.training.finalproject.model.domain.entity.CarProfile;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CarProfileSpecification implements Specification<CarProfile> {

    private SearchCriteria criteria;

    public CarProfileSpecification(SearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<CarProfile> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        if (criteria.getValue() == null){
            return builder.or(
                    criteria.getValues().stream().map(o -> builder.equal(root.get(criteria.getKey()), o))
                            .toArray(Predicate[]::new));
        } else {
            switch (criteria.getOperation()){
                case "eq":
                    return builder.equal(root.get(criteria.getKey()), criteria.getValue());
                case "ne":
                    return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
                case "gt":
                    return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
                case "lt":
                    return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
                case "lk":
                    return builder.like(root.get(criteria.getKey()), criteria.getValue().toString());
                default:
                    return null;
            }
        }
    }
}
