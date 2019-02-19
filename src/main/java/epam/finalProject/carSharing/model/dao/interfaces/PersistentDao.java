package epam.finalProject.carSharing.model.dao.interfaces;

import epam.finalProject.carSharing.model.domain.entity.Persistent;
import epam.finalProject.carSharing.model.domain.entity.User;

import java.util.List;

public interface PersistentDao<ID, T extends Persistent> {

    T getById(ID id);

    List<T> getAll();

    ID delete(T obj);

    ID update(T obj);

    ID save(T obj);


}
