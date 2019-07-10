package epam.training.finalproject.model.service.interfaces;

import epam.training.finalproject.model.domain.entity.Order;

import java.util.List;

public interface OrderService extends PersistentService<Long, Order> {

    Long updateOrderSetPaid(Order order);

    Long updateOrderStatus(Order order);

    List<Order> findOrdersByUserId(long idUser);
}
