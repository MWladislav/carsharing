package epam.training.finalproject.model.service.impls;

import epam.training.finalproject.model.dao.interfaces.OrderAdditionalInfoDao;
import epam.training.finalproject.model.domain.entity.OrderAdditionalInfo;
import epam.training.finalproject.model.service.interfaces.OrderAdditionalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderAdditionalInfoServiceImpl implements OrderAdditionalInfoService {

    @Autowired
    private OrderAdditionalInfoDao additionalInfoDao;

    @Override
    public List<OrderAdditionalInfo> findAdditionalInfoByOrderId(Long orderId) {
        return additionalInfoDao.findAdditionalInfoByOrderId(orderId);
    }

    @Override
    public List<OrderAdditionalInfo> findInfoByPayment(int price) {
        return additionalInfoDao.findInfoByPayment(price);
    }

    @Override
    public OrderAdditionalInfo getById(Long id) {
        return additionalInfoDao.getById(id);
    }

    @Override
    public List<OrderAdditionalInfo> getAll() {
        return additionalInfoDao.getAll();
    }

    @Override
    public Long delete(OrderAdditionalInfo obj) {
        return additionalInfoDao.delete(obj);
    }

    @Override
    public Long update(OrderAdditionalInfo obj) {
        return additionalInfoDao.update(obj);
    }

    @Override
    public Long save(OrderAdditionalInfo obj) {
        return additionalInfoDao.save(obj);
    }
}
