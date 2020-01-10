package epam.training.finalproject.model.dao.interfaces;

import epam.training.finalproject.model.domain.entity.User;

import java.util.Optional;

public interface UserDao extends AbstractEntityDao<Long, User> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Long banUser(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

}
