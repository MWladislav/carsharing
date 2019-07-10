package epam.training.finalproject.model.service.impls;

import epam.training.finalproject.exceptions.EntityNotFoundException;
import epam.training.finalproject.model.dao.interfaces.OrderAdditionalInfoDao;
import epam.training.finalproject.model.domain.entity.OrderAdditionalInfo;
import epam.training.finalproject.model.service.interfaces.OrderAdditionalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderAdditionalInfoServiceImpl implements OrderAdditionalInfoService {

    @Autowired
    private OrderAdditionalInfoDao additionalInfoDao;

    @Override
    public List<OrderAdditionalInfo> findAdditionalInfoByOrderId(Long orderId) {
        try {
            return additionalInfoDao.findAdditionalInfoByOrderId(orderId);
        }
        catch (DataAccessException ex){
            throw new EntityNotFoundException("Additional info for order with id "+orderId+" is not found",ex.getCause());
        }
    }

    @Override
    public List<OrderAdditionalInfo> findInfoByPayment(int price) {
        try {
            return additionalInfoDao.findInfoByPayment(price);
        }
        catch (DataAccessException ex){
            throw new EntityNotFoundException("Additional info with payment value "+price+" are not found",ex.getCause());
        }
    }

    @Override
    public OrderAdditionalInfo getById(Long id) {
        try {
            return additionalInfoDao.getById(id);
        }
        catch (DataAccessException ex){
            throw new EntityNotFoundException("Additional info with id "+id+" is not found",ex.getCause());
        }
    }

    @Override
    public List<OrderAdditionalInfo> getAll() {
        try {
            return additionalInfoDao.getAll();
        }
        catch (DataAccessException ex){
            throw new EntityNotFoundException("Additional info for orders are not found",ex.getCause());
        }
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
