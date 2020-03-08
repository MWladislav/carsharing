package epam.training.finalproject.model.service.interfaces;

import epam.training.finalproject.model.domain.dto.UserDto;
import epam.training.finalproject.model.domain.entity.User;

public interface UserService extends AbstractEntityService<Long, UserDto> {

    Long banUser(User user);

    User findByUsername(String username);

    User findByEmail(String email);

}