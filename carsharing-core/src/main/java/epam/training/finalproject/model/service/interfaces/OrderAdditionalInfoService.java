package epam.training.finalproject.model.service.interfaces;

import epam.training.finalproject.model.domain.entity.OrderAdditionalInfo;

import java.util.List;

public interface OrderAdditionalInfoService extends AbstractEntityService<Long, OrderAdditionalInfo> {

    List<OrderAdditionalInfo> findAdditionalInfoByOrderId(Long orderId);

    List<OrderAdditionalInfo> findInfoByPayment(int price);
}
