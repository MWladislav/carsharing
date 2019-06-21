package epam.training.finalproject.model.service.interfaces;

import epam.training.finalproject.model.domain.entity.Persistent;

import java.util.List;

public interface PersistentService<ID, T extends Persistent> {

    T getById(ID id);

    List<T> getAll();

    ID delete(T obj);

    ID update(T obj);

    ID save(T obj);

}
