package epam.finalProject.carSharing.model.service.impls;


import com.mysql.cj.util.StringUtils;
import epam.finalProject.carSharing.model.dao.interfaces.AddressDao;
import epam.finalProject.carSharing.model.dao.interfaces.OrderDao;
import epam.finalProject.carSharing.model.dao.interfaces.PassportDataDao;
import epam.finalProject.carSharing.model.dao.interfaces.UserDao;
import epam.finalProject.carSharing.model.domain.entity.Order;
import epam.finalProject.carSharing.model.domain.entity.User;
import epam.finalProject.carSharing.model.domain.entity.enums.Role;
import epam.finalProject.carSharing.model.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @Override
    public User findByUsername(String username) {
        if (username!=null)
            return userDao.findByUsername(username);
        return null;
    }

    @Override
    public User getByIdWithOrders(long id) {
        if(id>0){
            User user=userDao.getById(id);
            List<Order> userOrders=orderDao.findOrdersByUserId(id);
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
