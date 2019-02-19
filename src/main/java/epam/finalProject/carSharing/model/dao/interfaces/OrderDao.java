package epam.finalProject.carSharing.model.dao.interfaces;

import epam.finalProject.carSharing.model.domain.entity.Order;

import java.util.List;

public interface OrderDao extends PersistentDao<Long,Order> {

    List<Order> findOrdersByUserId(long idUser);

    List<Order> findOrdersByCarId(long idCar);

    Long updateOrderSetPaid(Order order);

    Long updateOrderStatus(Order order);
}
