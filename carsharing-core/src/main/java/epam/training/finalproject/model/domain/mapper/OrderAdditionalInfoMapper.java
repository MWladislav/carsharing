package epam.training.finalproject.model.domain.mapper;

import epam.training.finalproject.model.domain.entity.OrderAdditionalInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderAdditionalInfoMapper implements RowMapper<OrderAdditionalInfo> {

    @Override
    public OrderAdditionalInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        OrderAdditionalInfo orderAdditionalInfo = new OrderAdditionalInfo();
        orderAdditionalInfo.setId(resultSet.getInt("idOrderAdditionalInfo"));
        orderAdditionalInfo.setInfoDetails(resultSet.getString("info_details"));
        orderAdditionalInfo.setPaymentForViolation(resultSet.getInt("payment_for_violation"));
        orderAdditionalInfo.setDeleted(resultSet.getInt("is_deleted")==1);
        return orderAdditionalInfo;
    }
}
