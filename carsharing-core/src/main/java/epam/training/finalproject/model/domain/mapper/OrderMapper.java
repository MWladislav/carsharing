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
        order.setId(resultSet.getInt("id"));
        order.setConfirmationDate(resultSet.getTimestamp("confirmation_date").toLocalDateTime());
        order.setPaymentDate(resultSet.getTimestamp("payment_date").toLocalDateTime());
        order.setOrderDuration(resultSet.getInt("orderDuration"));
        order.setStatus(OrderStatus.valueOf(resultSet.getString("order_status").toUpperCase()));
        order.setPrice(resultSet.getInt("price"));
        order.setDeleted(resultSet.getInt("deleted") == 1);
        return order;
    }
}
