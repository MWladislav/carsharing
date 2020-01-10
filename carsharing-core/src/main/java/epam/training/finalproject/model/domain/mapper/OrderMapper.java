package epam.training.finalproject.model.domain.mapper;

import epam.training.finalproject.model.domain.entity.Order;
import epam.training.finalproject.model.domain.entity.enums.OrderStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("idOrder"));
        order.setPrice(resultSet.getInt("price"));
        order.setStatus(OrderStatus.valueOf(resultSet.getString("order_status")));
        order.setConfirmationDate(resultSet.getTimestamp("confirmation_date").toLocalDateTime());
        order.setPaymentDate(resultSet.getTimestamp("payment_date").toLocalDateTime());
        order.setOrderDuration(resultSet.getInt("orderDuration"));
        order.setCarId(resultSet.getInt("car_id"));
        order.setUserId(resultSet.getInt("user_id"));
        order.setDeleted(resultSet.getInt("is_deleted")==1);
        return order;
    }
}
