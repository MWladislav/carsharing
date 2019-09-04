package epam.training.finalproject.model.dao.interfaces;

import epam.training.finalproject.model.domain.entity.User;

import java.util.Optional;

public interface UserDao extends PersistentDao<Long, User> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

}
