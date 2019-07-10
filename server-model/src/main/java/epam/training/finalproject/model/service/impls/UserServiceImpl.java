package epam.training.finalproject.model.service.impls;


import epam.training.finalproject.exceptions.EntityNotFoundException;
import epam.training.finalproject.model.dao.interfaces.UserDao;
import epam.training.finalproject.model.domain.entity.Order;
import epam.training.finalproject.model.domain.entity.Role;
import epam.training.finalproject.model.domain.entity.User;
import epam.training.finalproject.model.domain.entity.enums.RoleName;
import epam.training.finalproject.model.service.interfaces.OrderAdditionalInfoService;
import epam.training.finalproject.model.service.interfaces.OrderService;
import epam.training.finalproject.model.service.interfaces.RoleService;
import epam.training.finalproject.model.service.interfaces.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsername(String username) {
        try {
            return userDao.findByUsername(username);
        }
        catch (DataAccessException ex){
            throw new EntityNotFoundException("User with username "+username+" is not found",ex.getCause());
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        return userDao.existsByUsername(username);
    }

    @Override
    public User getById(Long id){
        try {
            return userDao.getById(id);
        }
        catch (DataAccessException ex){
            throw new EntityNotFoundException("User with id "+id+" is not found",ex.getCause());
        }
    }

    @Override
    public List<User> getAll(){
        try {
            return userDao.getAll();
        }
        catch (DataAccessException ex){
            throw new EntityNotFoundException("Users are not found",ex.getCause());
        }
    }

    @Override
    public Long delete(User user) {
        return userDao.delete(user);
    }

    @Override
    public Long update(User user) {
        return userDao.update(user);
    }

    @Override
    public Long save(User user) {
        user.setActive(true);
//        user.getRoles().forEach(role -> roleService.save(user.getId(),role.getId()));
        return userDao.save(user);
    }
}
