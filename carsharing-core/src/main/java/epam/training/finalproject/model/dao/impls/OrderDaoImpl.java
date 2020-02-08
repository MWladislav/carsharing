package epam.training.finalproject.model.dao.impls;

import epam.training.finalproject.model.dao.interfaces.OrderDao;
import epam.training.finalproject.model.domain.entity.Order;
import epam.training.finalproject.model.domain.mapper.OrderMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class OrderDaoImpl implements OrderDao {

    private static Logger LOGGER= Logger.getLogger(Order.class);

    private final String SQL_FIND_ORDERS_BY_USER_ID = "select * from orders o where o.user_id=?";
    private final String SQL_FIND_ORDERS_BY_CAR_ID = "select * from orders o where o.car_id=?";
    private final String SQL_GET_ALL = "select * from orders";
    private final String SQL_GET_ORDER_BY_ID = "select * from orders where idOrder=?";
    private final String SQL_DELETE_ORDER = "update orders set deleted=? where idOrder = ?";
    private final String SQL_UPDATE_ORDER_SET_CONFIRMED = "update orders set confirmation_date=?, order_status=?, " +
            "price=? where idOrder = ?";
    private final String SQL_UPDATE_ORDER_STATUS = "update orders set order_status=? where idOrder = ?";
    private final String SQL_UPDATE_ORDER_SET_PAID = "update orders set order_status=?, payment_date=? where idOrder = ?";
    private final String SQL_SAVE_ORDER = "insert into orders(user_id,car_id,orderDuration,order_status) values (?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Order> findOrdersByCarId(long idCar) {
        try {
            return jdbcTemplate.query(SQL_FIND_ORDERS_BY_CAR_ID, new OrderMapper(),idCar);
        }
        catch (DataAccessException ex){
            LOGGER.error("Any orders with car id "+idCar+" are not found",ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Order> findOrdersByUserId(long idUser) {
        try {
            return jdbcTemplate.query(SQL_FIND_ORDERS_BY_USER_ID,new OrderMapper(),idUser);
        }
        catch (DataAccessException ex){
            LOGGER.error("Any orders with car id "+idUser+" are not found",ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Order> getById(Long id) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_GET_ORDER_BY_ID,
                    new OrderMapper(), id)));
        }
        catch (DataAccessException ex){
            LOGGER.error("Order with id "+id+" is not found",ex.getCause());
            return Optional.empty();
        }
    }

    @Override
    public List<Order> getAll() {
        try {
            return jdbcTemplate.query(SQL_GET_ALL,new OrderMapper());
        }
        catch (DataAccessException ex){
            LOGGER.error("Any orders are not found",ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public Long delete(Order order) {
        try {
            return (long) jdbcTemplate.update(SQL_DELETE_ORDER,order.isDeleted(),order.getId());
        }
        catch (DataAccessException ex){
            LOGGER.error("Order with id "+order.getId()+" has invalid credentials",ex.getCause());
            return -1L;
        }
    }

    @Override
    public Long updateOrderStatus(Order order){
        try {
            return (long) jdbcTemplate.update(SQL_UPDATE_ORDER_STATUS,order.getStatus(),order.getId());
        }
        catch (DataAccessException ex){
            LOGGER.error("Order with id "+order.getId()+" has invalid credentials",ex.getCause());
            return -1L;
        }
    }

    @Override
    public Long updateOrderSetPaid(Order order){
        try {
            return (long) jdbcTemplate.update(SQL_UPDATE_ORDER_SET_PAID, order.getStatus(), order.getPaymentDate(),
                    order.getId());
        }
        catch (DataAccessException ex){
            LOGGER.error("Order with id "+order.getId()+" has invalid credentials",ex.getCause());
            return -1L;
        }
    }

    @Override
    public Long update(Order order) {
        try {
            return (long) jdbcTemplate.update(SQL_UPDATE_ORDER_SET_CONFIRMED,order.getConfirmationDate(),
                    order.getStatus(),order.getPrice(),order.getId());
        }
        catch (DataAccessException ex){
            LOGGER.error("Order with id "+order.getId()+" has invalid credentials",ex.getCause());
            return -1L;
        }
    }

    @Override
    public Long save(Order order) {
        try {
            return (long) jdbcTemplate.update(SQL_SAVE_ORDER, order.getUserId(),order.getCarId(),
                    order.getOrderDuration(),order.getStatus());
        }
        catch (DataAccessException ex){
            LOGGER.error("Order with has invalid credentials",ex.getCause());
            return -1L;
        }
    }
}
