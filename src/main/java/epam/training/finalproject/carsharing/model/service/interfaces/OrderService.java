package epam.training.finalproject.carsharing.model.service.interfaces;

import epam.training.finalproject.carsharing.model.domain.entity.Order;

import java.util.List;

public interface OrderService extends PersistentService<Long, Order> {

    Order getOrderWithOrderAdditionalInfo(Long id);

    Long updateOrderSetPaid(Order order);

    Long updateOrderStatus(Order order);

    List<Order> findOrdersByUserId(long idUser);
}
