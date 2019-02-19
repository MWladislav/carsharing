package epam.finalProject.carSharing.model.dao.interfaces;

import epam.finalProject.carSharing.model.domain.entity.OrderAdditionalInfo;

import java.util.List;

public interface OrderAdditionalInfoDao extends PersistentDao<Long, OrderAdditionalInfo> {

    List<OrderAdditionalInfo> findInfoByPayment(int price);

    List<OrderAdditionalInfo> findAdditionalInfoByOrderId(Long orderId);
}
