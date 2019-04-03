package epam.training.finalproject.carsharing.model.service.impls;


import epam.training.finalproject.carsharing.model.dao.interfaces.UserDao;
import epam.training.finalproject.carsharing.model.domain.entity.Order;
import epam.training.finalproject.carsharing.model.domain.entity.User;
import epam.training.finalproject.carsharing.model.domain.entity.enums.Role;
import epam.training.finalproject.carsharing.model.service.interfaces.OrderAdditionalInfoService;
import epam.training.finalproject.carsharing.model.service.interfaces.OrderService;
import epam.training.finalproject.carsharing.model.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderAdditionalInfoService additionalInfoService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @Override
    public User findByUsername(String username) throws DataAccessException {
        if (username!=null && !username.isEmpty()){
            return userDao.findByUsername(username);
        }
        return null;
    }

    @Override
    public User getByIdWithOrders(long id) {
        if(id>0){
            User user=userDao.getById(id);
            List<Order> userOrders= orderService.findOrdersByUserId(id);
            user.setOrders(userOrders);
            return user;
        }
        return null;
    }

    @Override
    public User getByIdWithOrdersAndAdInfo(long id) {
        if(id>0){
            User user=userDao.getById(id);
            List<Order> userOrders= orderService.findOrdersByUserId(id);
            for (int i=0;i<userOrders.size();i++){
                Order order=userOrders.get(i);
                order.setOrderAdditionalInfo(additionalInfoService.findAdditionalInfoByOrderId(order.getId()));
            }
            user.setOrders(userOrders);
            return user;
        }
        return null;
    }

    @Override
    public User getByIdWithPassportDataAndAddress(long id) {
        if (id>0)
            return userDao.getByIdWithPassportData(id);
        return null;
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRole(Role.ROLE_USER);
        return userDao.save(user);
    }
}
