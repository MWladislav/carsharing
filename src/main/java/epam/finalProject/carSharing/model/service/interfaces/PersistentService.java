package epam.finalProject.carSharing.model.service.interfaces;

import epam.finalProject.carSharing.model.domain.entity.Persistent;

import java.util.List;

public interface PersistentService<ID, T extends Persistent> {

    T getById(ID id);

    List<T> getAll();

    ID delete(T obj);

    ID update(T obj);

    ID save(T obj);

}
