package epam.training.finalproject.model.service.interfaces;

import epam.training.finalproject.model.domain.entity.AbstractEntity;

import java.util.List;

public interface AbstractEntityService<ID, T extends AbstractEntity> {

    T getById(ID id);

    List<T> getAll();

    ID delete(T obj);

    ID update(T obj);

    ID save(T obj);

}
