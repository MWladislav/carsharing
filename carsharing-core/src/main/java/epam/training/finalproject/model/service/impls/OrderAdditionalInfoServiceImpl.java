package epam.training.finalproject.model.service.impls;

import epam.training.finalproject.exception.EntityNotFoundException;
import epam.training.finalproject.exception.OperationException;
import epam.training.finalproject.model.dao.interfaces.OrderAdditionalInfoDao;
import epam.training.finalproject.model.domain.entity.OrderAdditionalInfo;
import epam.training.finalproject.model.service.interfaces.OrderAdditionalInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderAdditionalInfoServiceImpl implements OrderAdditionalInfoService {

    private static final Logger LOGGER = Logger.getLogger(OrderAdditionalInfoServiceImpl.class);

    @Autowired
    private OrderAdditionalInfoDao additionalInfoDao;

    @Override
    public List<OrderAdditionalInfo> findAdditionalInfoByOrderId(Long orderId) {
        if (orderId > 0) {
            List<OrderAdditionalInfo> orderAdditionalInfo = additionalInfoDao.findAdditionalInfoByOrderId(orderId);
            if (orderAdditionalInfo.isEmpty()) {
                LOGGER.debug("Order additional info is empty!");
                throw new EntityNotFoundException("Additional info for order with id " + orderId + " is not found");
            }
            return orderAdditionalInfo;
        }
        LOGGER.debug("Order id is invalid!");
        throw new IllegalArgumentException("Order id is invalid");
    }

    @Override
    public List<OrderAdditionalInfo> findInfoByPayment(int price) {
        if (price > 0){
            List<OrderAdditionalInfo> additionalInfo = additionalInfoDao.findInfoByPayment(price);
            if (additionalInfo.isEmpty()){
                LOGGER.debug("Order additional info is empty!");
                throw new EntityNotFoundException("Additional info with payment value " + price + " are not found");
            }
            return additionalInfo;
        }
        LOGGER.debug("Price value is invalid!");
        throw new IllegalArgumentException("Price value is invalid");
    }

    @Override
    public OrderAdditionalInfo getById(Long id) {
        if (id > 0){
            return additionalInfoDao.getById(id).orElseThrow(() -> {
                LOGGER.debug("Order additional info is null!");
                return new EntityNotFoundException("Additional info with id " + id + " is not found");
            });
        }
        LOGGER.debug("Additional info id is invalid!");
        throw new IllegalArgumentException("Additional info id is invalid");
    }

    @Override
    public List<OrderAdditionalInfo> getAll() {
        List<OrderAdditionalInfo> additionalInfo = additionalInfoDao.getAll();
        if (additionalInfo.isEmpty()){
            LOGGER.debug("Additional info is empty!");
            throw new EntityNotFoundException("Additional info for orders are not found");
        }
        return additionalInfo;
    }

    @Override
    public Long delete(OrderAdditionalInfo info) {
        if (info != null){
            Long result = additionalInfoDao.delete(info);
            if (result == -1L){
                LOGGER.debug("Additional info credentials is invalid!");
                throw new OperationException("Order additional info with id " + info.getId() + " has invalid credentials");
            }
            return result;
        }
        LOGGER.debug("Additional info is null!");
        throw new IllegalArgumentException("Additional info is invalid");
    }

    @Override
    public Long update(OrderAdditionalInfo info) {
        if (info != null){
            Long result = additionalInfoDao.update(info);
            if (result == -1L){
                LOGGER.debug("Additional info credentials is invalid!");
                throw new OperationException("Order additional info with id " + info.getId() + " has invalid credentials");
            }
            return result;
        }
        LOGGER.debug("Additional info is null!");
        throw new IllegalArgumentException("Additional info is invalid");
    }

    @Override
    public Long save(OrderAdditionalInfo info) {
        if (info != null){
            info.setDeleted(true);
            Long result = additionalInfoDao.save(info);
            if (result == -1L){
                LOGGER.debug("Additional info credentials is invalid!");
                throw new OperationException("Order additional info with id " + info.getId() + " has invalid credentials");
            }
            return result;
        }
        LOGGER.debug("Additional info is null!");
        throw new IllegalArgumentException("Additional info is invalid");
    }
}
