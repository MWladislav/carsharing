package epam.training.finalproject.carsharing.model.service.interfaces;

import epam.training.finalproject.carsharing.model.domain.entity.OrderAdditionalInfo;

import java.util.List;

public interface OrderAdditionalInfoService extends PersistentService<Long, OrderAdditionalInfo> {

    List<OrderAdditionalInfo> findAdditionalInfoByOrderId(Long orderId);

    List<OrderAdditionalInfo> findInfoByPayment(int price);
}
