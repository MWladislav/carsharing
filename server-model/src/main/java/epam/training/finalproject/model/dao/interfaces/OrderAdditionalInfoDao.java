package epam.training.finalproject.model.dao.interfaces;

import epam.training.finalproject.model.domain.entity.OrderAdditionalInfo;

import java.util.List;

public interface OrderAdditionalInfoDao extends AbstractEntityDao<Long, OrderAdditionalInfo> {

    List<OrderAdditionalInfo> findInfoByPayment(int price);

    List<OrderAdditionalInfo> findAdditionalInfoByOrderId(Long orderId);
}
