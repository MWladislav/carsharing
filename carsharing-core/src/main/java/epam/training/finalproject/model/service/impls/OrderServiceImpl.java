package epam.training.finalproject.model.service.impls;

import epam.training.finalproject.exception.EntityNotFoundException;
import epam.training.finalproject.exception.OperationException;
import epam.training.finalproject.model.dao.interfaces.OrderDao;
import epam.training.finalproject.model.domain.entity.Order;
import epam.training.finalproject.model.domain.entity.enums.OrderStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl /*implements OrderService*/ {

    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

    private final long orderDurationTimeOut = 30;

    @Autowired
    private OrderDao orderDao;

    public List<Order> findOrdersByUserId(Long userId) {
        if (userId > 0) {
            List<Order> orders = orderDao.findOrdersByUserId(userId);
            if (orders.isEmpty()) {
                LOGGER.debug("Orders is empty!");
                throw new EntityNotFoundException("Any orders are not found for user with id " + userId);
            }
            return orders;
        }
        LOGGER.debug("User id is invalid!");
        throw new IllegalArgumentException("User id is invalid");
    }

//    @Override
    public Order getById(Long id) {
        if (id > 0) {
            return orderDao.getById(id).orElseThrow(() -> {
                LOGGER.debug("Order is null!");
                return new EntityNotFoundException("Order with id " + id + " is not found");
            });
        }
        LOGGER.debug("Order id is invalid!");
        throw new IllegalArgumentException("Order id is invalid");
    }

//    @Override
    public List<Order> getAll() {
        List<Order> orders = orderDao.getAll();
        if (orders.isEmpty()) {
            LOGGER.debug("Orders is empty!");
            throw new EntityNotFoundException("Any orders are not found");
        }
        return orders;
    }

    //TODO add logic to delete/update operations (example save())
//    @Override
    public Long delete(Order order) {
        if (order != null && order.getStatus().equals(OrderStatus.FINISHED)) {
            order.setDeleted(true);
            return orderDao.delete(order);
        }
        throw new IllegalArgumentException("Order is invalid");
    }

//    @Override
    public Long updateOrderSetPaid(Order order) {
        if (order != null && order.getPaymentDate().isBefore(order.getConfirmationDate().plusMinutes(orderDurationTimeOut)) && order.getStatus() == OrderStatus.PAID)
            return orderDao.updateOrderSetPaid(order);
        throw new IllegalArgumentException("Order is invalid");
    }

//    @Override
    public Long updateOrderStatus(Order order) {
        if (order != null)
            return orderDao.updateOrderStatus(order);
        throw new IllegalArgumentException("Order is invalid");
    }

//    @Override
    public Long update(Order order) {
        if (order != null && order.getConfirmationDate() != null && order.getStatus() == OrderStatus.CONFIRMED && order.getPrice() > 0)
            return orderDao.update(order);
        throw new IllegalArgumentException("Order is invalid");
    }

//    @Override
    public Long save(Order order) {
        if (order != null) {
            order.setStatus(OrderStatus.CREATED);
            Long result = orderDao.save(order);
            if (result == -1L) {
                LOGGER.debug("Invalid order credentials!");
                throw new OperationException("Order with id " + order.getId() + " has invalid credentials");
            }
            return result;
        }
        LOGGER.debug("Order is null!");
        throw new IllegalArgumentException("Order is invalid");
    }
}
