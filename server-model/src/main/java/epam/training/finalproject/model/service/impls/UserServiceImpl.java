package epam.training.finalproject.model.service.impls;


import epam.training.finalproject.exceptions.DaoOperationException;
import epam.training.finalproject.exceptions.DuplicateEntityCredentialsException;
import epam.training.finalproject.exceptions.EntityNotFoundException;
import epam.training.finalproject.model.dao.interfaces.UserDao;
import epam.training.finalproject.model.domain.entity.User;
import epam.training.finalproject.model.service.interfaces.RoleService;
import epam.training.finalproject.model.service.interfaces.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static Logger LOGGER=Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleService roleService;

    @Transactional
    @Override
    public User findByUsername(String username) {
        try {
            return userDao.findByUsername(username).get();
        }
        catch (DataAccessException ex){
            throw new EntityNotFoundException("User with username "+username+" is not found",ex.getCause());
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            return userDao.findByUsername(email).get();
        }
        catch (DataAccessException ex){
            throw new EntityNotFoundException("User with email "+email+" is not found",ex.getCause());
        }
    }


//    @Override
//    public boolean existsByUsername(String username) {
//        return userDao.existsByUsername(username);
//    }
//
//    @Override
//    public boolean existsByEmail(String email) {
//        return userDao.existsByEmail(email);
//    }

    @Transactional
    @Override
    public User getById(Long id){
        try {
            User user=userDao.getById(id).get();
            if (!user.isActive())
                throw new EntityNotFoundException("User with id "+id+" is not found");
            return user;
        }
        catch (DataAccessException ex){
            throw new EntityNotFoundException("User with id "+id+" is not found",ex.getCause());
        }
    }

    @Transactional
    @Override
    public List<User> getAll(){
        try {
            return userDao.getAll();
        }
        catch (DataAccessException ex){
            throw new EntityNotFoundException("Users are not found",ex.getCause());
        }
    }

    @Transactional
    @Override
    public Long delete(User user) {
        try {
            boolean isUserExist=userDao.findByUsername(user.getUsername())!=null;
            if (!isUserExist)
                throw new EntityNotFoundException("User with username "+user.getUsername()+" is not found");
            user.setActive(false);
            return userDao.delete(user);
        }
        catch (DataAccessException ex){
            throw new DaoOperationException("User with id "+user.getId()+" and username "+user.getUsername()+" has invalid credentials",ex.getCause());
        }
    }

    @Transactional
    @Override
    public Long update(User user) {
        try {
            boolean isUserExist=userDao.findByUsername(user.getUsername())!=null;
            if (!isUserExist)
                throw new EntityNotFoundException("User with username "+user.getUsername()+" is not found");
            return userDao.update(user);
        }
        catch (DataAccessException ex){
            throw new DaoOperationException("User with id "+user.getId()+" and username "+user.getUsername()+" has invalid credentials",ex.getCause());
        }
    }


    @Override
    public Long save(User user) {
        try {

            return userDao.save(user);
        }
        catch (DuplicateEntityCredentialsException ex){
            LOGGER.error(ex.getMessage(),ex.getCause());
            return -1L;
        }
    }
}
