package epam.training.finalproject.model.service.impls;

import epam.training.finalproject.model.dao.interfaces.OrderAdditionalInfoDao;
import epam.training.finalproject.model.dao.interfaces.OrderDao;
import epam.training.finalproject.model.domain.entity.Order;
import epam.training.finalproject.model.domain.entity.OrderAdditionalInfo;
import epam.training.finalproject.model.domain.entity.enums.OrderStatus;
import epam.training.finalproject.model.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final long orderDurationTimeOut=30;

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderAdditionalInfoDao infoDao;

    public List<Order> findOrdersByUserId(long idUser){
        return orderDao.findOrdersByUserId(idUser);
    }

    @Override
    public Order getOrderWithOrderAdditionalInfo(Long id) {
        Order order=orderDao.getById(id);
        List<OrderAdditionalInfo> infoList=infoDao.findAdditionalInfoByOrderId(id);
        order.setOrderAdditionalInfo(infoList);
        return order;
    }

    @Override
    public Order getById(Long id) {
        if (id>0)
            return orderDao.getById(id);
        return null;
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public Long delete(Order order) {
        if (order!=null)
            return orderDao.delete(order);
        return 0L;
    }

    @Override
    public Long updateOrderSetPaid(Order order) {
        if (order!=null && order.getPaymentDate().isBefore(order.getConfirmationDate().plusMinutes(orderDurationTimeOut)) && order.getStatus()== OrderStatus.PAID)
            return orderDao.updateOrderSetPaid(order);
        return 0L;
    }

    @Override
    public Long updateOrderStatus(Order order) {
        if (order!=null)
            return orderDao.updateOrderStatus(order);
        return 0L;
    }

    @Override
    public Long update(Order order) {
        if (order!=null && order.getConfirmationDate()!=null && order.getStatus()==OrderStatus.CONFIRMED && order.getPrice()>0)
            return orderDao.update(order);
        return 0L;
    }

    @Override
    public Long save(Order order) {
        if (order!=null && order.getOrderDuration()>0 && order.getStatus()== OrderStatus.CREATED && order.getUserId()>0 &&  order.getCarId()>0)
            return orderDao.save(order);
        return 0L;
    }
}
