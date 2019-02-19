package epam.finalProject.carSharing.model.service.interfaces;

import epam.finalProject.carSharing.model.domain.entity.Order;

public interface OrderService extends PersistentService<Long, Order> {

    Order getOrderWithOrderAdditionalInfo(Long id);

    Long updateOrderSetPaid(Order order);

    Long updateOrderStatus(Order order);
}
