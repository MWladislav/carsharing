package epam.training.finalproject.model.service.interfaces;

import epam.training.finalproject.model.domain.entity.Order;

import java.util.List;

public interface OrderService extends AbstractEntityService<Long, Order> {

    Long updateOrderSetPaid(Order order);

    Long updateOrderStatus(Order order);

    List<Order> findOrdersByUserId(Long userId);
}
