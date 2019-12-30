package epam.training.finalproject.model.dao.interfaces;

import epam.training.finalproject.model.domain.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface AbstractEntityDao<ID, T extends AbstractEntity> {

    Optional<T> getById(ID id);

    List<T> getAll();

    ID delete(T obj);

    ID update(T obj);

    ID save(T obj);


}
