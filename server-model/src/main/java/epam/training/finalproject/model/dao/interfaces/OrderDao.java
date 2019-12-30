package epam.training.finalproject.model.dao.interfaces;

import epam.training.finalproject.model.domain.entity.Order;

import java.util.List;

public interface OrderDao extends AbstractEntityDao<Long,Order> {

    List<Order> findOrdersByUserId(long idUser);

    List<Order> findOrdersByCarId(long idCar);

    Long updateOrderSetPaid(Order order);

    Long updateOrderStatus(Order order);
}
