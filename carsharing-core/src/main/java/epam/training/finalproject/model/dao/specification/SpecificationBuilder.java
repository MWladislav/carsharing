package epam.training.finalproject.model.dao.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SpecificationBuilder<T> {

    private List<SearchCriteria> params;

    public SpecificationBuilder() {
        params = new ArrayList<>();
    }

    public SpecificationBuilder(List<SearchCriteria> params) {
        this.params = params;
    }

    public final SpecificationBuilder<T> with(final String key, final String operation, List values){
        params.add(new SearchCriteria(key,operation,values));
        return this;
    }

    public Specification<T> build(Function<SearchCriteria, Specification<T>> converter){
        if (params.size() != 0){
            return null;
        }
        final List<Specification<T>> specs = params.stream()
                .map(converter)
                .collect(Collectors.toCollection(ArrayList::new));

        Specification<T> result = specs.get(0);

        for (int i = 1; i < specs.size(); i++){
            result = Objects.requireNonNull(Specification.where(result)).and(specs.get(i));
        }
        return result;
    }
}
