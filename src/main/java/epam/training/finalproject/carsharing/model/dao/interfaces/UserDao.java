package epam.training.finalproject.carsharing.model.dao.interfaces;

import epam.training.finalproject.carsharing.model.domain.entity.User;

public interface UserDao extends PersistentDao<Long,User> {

    User findByUsername(String username);

    User getByIdWithPassportData(Long id);

}
