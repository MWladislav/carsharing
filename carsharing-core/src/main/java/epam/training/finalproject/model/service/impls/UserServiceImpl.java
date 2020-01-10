package epam.training.finalproject.model.service.impls;


import epam.training.finalproject.exception.DuplicateEntityCredentialsException;
import epam.training.finalproject.exception.EntityNotFoundException;
import epam.training.finalproject.exception.OperationException;
import epam.training.finalproject.model.dao.interfaces.UserDao;
import epam.training.finalproject.model.domain.entity.User;
import epam.training.finalproject.model.service.interfaces.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;


    @Transactional
    @Override
    public User findByUsername(String username) {
        if (username != null && !StringUtils.isEmpty(username)) {
            User user = userDao.findByUsername(username).orElseThrow(() -> {
                LOGGER.debug("User is null!");
                return new EntityNotFoundException("User with username " +
                        username + " is not found");
            });
            if (!user.isActive()) {
                LOGGER.debug("User is not active!");
                throw new EntityNotFoundException("User with username " + username + " is not found");
            }
            return user;
        }
        LOGGER.debug("findByUsername operation: username is null or empty!");
        throw new IllegalArgumentException("Username is invalid");
    }

    @Transactional
    @Override
    public User findByEmail(String email) {
        if (email != null && !StringUtils.isEmpty(email) && email.matches("^.+@.+\\.\\w{2,3}$")) {
            User user = userDao.findByEmail(email).orElseThrow(() -> {
                LOGGER.debug("User is null!");
                return new EntityNotFoundException("User with email " + email + " is not found");
            });
            if (!user.isActive()){
                LOGGER.debug("User is not active!");
                throw new EntityNotFoundException("User with email " + email + " is not found");
            }
            return user;
        }
        LOGGER.debug("findByEmail operation: email is invalid");
        throw new IllegalArgumentException("Email is invalid");
    }

    @Transactional
    @Override
    public User getById(Long id) {
        if (id > 0) {
            User user = userDao.getById(id).orElseThrow(() -> {
                LOGGER.debug("User is null!");
                return new EntityNotFoundException("User with id " + id + " is not found");
            });
            if (!user.isActive()) {
                LOGGER.debug("User is not active!");
                throw new EntityNotFoundException("User with id " + id + " is not found");
            }
            return user;
        }
        throw new IllegalArgumentException("Id is invalid");
    }

    @Transactional
    @Override
    public List<User> getAll() {
        List<User> users = userDao.getAll();
        if (users.isEmpty()) {
            LOGGER.debug("Users is empty!");
            throw new EntityNotFoundException("Any users are not found");
        }
        return users;
    }
    //TODO rethink the meaning of this method, maybe it unnecessary
    @Override
    public Long banUser(User user) {
        if (user != null) {
            user.setActive(false);
            Long result = userDao.banUser(user);
            if (result == -1L){
                LOGGER.debug("Invalid user credentials!");
                throw new OperationException("User with id " + user.getId() + " and username " + user.getUsername() +
                        " has invalid credentials");
            }
            return result;
        }
        LOGGER.debug("ban operation: user is null or doesn't exist!");
        throw new IllegalArgumentException("User is invalid");
    }

    @Transactional
    @Override
    public Long delete(User user) {
        if (user != null && userDao.existsByUsername(user.getUsername())) {
            user.setDeleted(true);
            user.setActive(false);

            Long result = userDao.delete(user);
            if (result == -1L){
                LOGGER.debug("Invalid user credentials!");
                throw new OperationException("User with id " + user.getId() + " and username " + user.getUsername() +
                        " has invalid credentials");
            }
            return result;
        }
        LOGGER.debug("delete operation: user is null or doesn't exist!");
        throw new IllegalArgumentException("User is invalid");
    }

    @Transactional
    @Override
    public Long update(User user) {
        if (user != null && userDao.existsByUsername(user.getUsername())) {
            Long result = userDao.update(user);
            if (result == -1L){
                LOGGER.debug("Invalid user credentials!");
                throw new OperationException("User with id " + user.getId() + " and username " + user.getUsername() +
                        " has invalid credentials");
            }
            return result;
        }
        LOGGER.debug("update operation: user is null or doesn't exist!");
        throw new IllegalArgumentException("User is invalid");
    }


    @Override
    public Long save(User user) {
        if (user != null) {
            if (userDao.existsByUsername(user.getUsername())){
                LOGGER.debug("Duplicate username!");
                throw new DuplicateEntityCredentialsException("Username " + user.getUsername() + " is already taken");
            }
            if (userDao.existsByEmail(user.getEmail())){
                LOGGER.debug("Duplicate email!");
                throw new DuplicateEntityCredentialsException("Email " + user.getEmail() + " is already taken");
            }
            user.setActive(true);
            Long result = userDao.save(user);
            if (result == -1L){
                LOGGER.debug("Invalid user credentials!");
                throw new OperationException("User with id " + user.getId() + " and username " + user.getUsername() +
                        " has invalid credentials");
            }
            return result;
        }
        LOGGER.debug("save operation: user is null!");
        throw new IllegalArgumentException("User is null");
    }
}
