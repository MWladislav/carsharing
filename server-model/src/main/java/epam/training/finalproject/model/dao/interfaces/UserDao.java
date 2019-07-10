package epam.training.finalproject.model.dao.interfaces;

import epam.training.finalproject.model.domain.entity.User;

public interface UserDao extends PersistentDao<Long,User> {

    User findByUsername(String username);

    boolean existsByUsername(String username);

}
