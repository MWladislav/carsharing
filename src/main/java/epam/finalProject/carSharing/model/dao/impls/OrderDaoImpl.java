package epam.finalProject.carSharing.model.dao.impls;

import epam.finalProject.carSharing.model.dao.interfaces.OrderDao;
import epam.finalProject.carSharing.model.domain.entity.Order;
import epam.finalProject.carSharing.model.domain.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    private final String SQL_FIND_ORDERS_BY_USER_ID = "select o.idOrder, o.confirmation_date, o.payment_date, o.orderDuration, o.order_status, o.price, o.car_id, o.user_id " +
            "from `order` o where o.user_id=?";
    private final String SQL_FIND_ORDERS_BY_CAR_ID = "select o.idOrder, o.confirmation_date, o.payment_date, o.orderDuration, o.order_status, o.price, o.user_id " +
            "from `order` o where o.car_id=?";
    private final String SQL_GET_ALL = "select * from `order`";
    private final String SQL_GET_ORDER_BY_ID = "select * from `order` where idOrder=?";
    private final String SQL_DELETE_ORDER = "delete from `order` where idOrder = ?";
    private final String SQL_UPDATE_ORDER_SET_CONFIRMED = "update `order` set confirmation_date=?, order_status=?, price=? where idOrder = ?";
    private final String SQL_UPDATE_ORDER_STATUS = "update `order` set order_status=? where idOrder = ?";
    private final String SQL_UPDATE_ORDER_SET_PAID = "update `order` set order_status=?, payment_date=? where idOrder = ?";
    private final String SQL_SAVE_ORDER = "insert into `order`(user_id,car_id,orderDuration,order_status) values (?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Order> findOrdersByCarId(long idCar) {
        return jdbcTemplate.query(SQL_FIND_ORDERS_BY_CAR_ID, new OrderMapper(),idCar);
    }

    @Override
    public List<Order> findOrdersByUserId(long idUser) {
        return jdbcTemplate.query(SQL_FIND_ORDERS_BY_USER_ID,new OrderMapper(),idUser);
    }

    @Override
    public Order getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_GET_ORDER_BY_ID,new OrderMapper(),id);
    }

    @Override
    public List<Order> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL,new OrderMapper());
    }

    @Override
    public Long delete(Order order) {
        return (long) jdbcTemplate.update(SQL_DELETE_ORDER,order.getId());
    }

    @Override
    public Long updateOrderStatus(Order order){
        return (long) jdbcTemplate.update(SQL_UPDATE_ORDER_STATUS,order.getStatus(),order.getId());
    }

    @Override
    public Long updateOrderSetPaid(Order order){
        return (long) jdbcTemplate.update(SQL_UPDATE_ORDER_SET_PAID, order.getStatus(), order.getPaymentDate(), order.getId());
    }

    @Override
    public Long update(Order order) {
        return (long) jdbcTemplate.update(SQL_UPDATE_ORDER_SET_CONFIRMED,order.getConfirmationDate(),order.getStatus(),order.getPrice(),order.getId());
    }

    @Override
    public Long save(Order order) {
        return (long) jdbcTemplate.update(SQL_SAVE_ORDER, order.getUserId(),order.getCarId(), order.getOrderDuration(),order.getStatus());
    }
}
