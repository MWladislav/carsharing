package epam.training.finalproject.model.service.interfaces;

import epam.training.finalproject.model.domain.entity.User;

public interface UserService extends PersistentService<Long,User>{

    User findByUsername(String username);

    User findByEmail(String email);

}
