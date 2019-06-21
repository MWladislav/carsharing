package epam.training.finalproject.model.dao.impls;

import epam.training.finalproject.model.dao.interfaces.OrderAdditionalInfoDao;
import epam.training.finalproject.model.domain.entity.OrderAdditionalInfo;
import epam.training.finalproject.model.domain.mapper.OrderAdditionalInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class OrderAdditionalInfoDaoImpl implements OrderAdditionalInfoDao {
    private final String SQL_GET_ALL = "select * from orderadditionalinfo";
    private final String SQL_FIND_ADDITIONAL_INFO_BY_PAYMENT ="select * from orderadditionalinfo where payment_for_violation=?";
    private final String SQL_GET_ADDITIONAL_INFO_BY_ID = "select * from orderadditionalinfo where idOrderAdditionalInfo=?";
    private final String SQL_DELETE_ADDITIONAL_INFO = "delete from orderadditionalinfo where idOrderAdditionalInfo = ?";
    private final String SQL_UPDATE_ADDITIONAL_INFO = "update orderadditionalinfo set info_details=?, payment_for_violation=?" +
            " where idOrderAdditionalInfo = ?";
    private final String SQL_SAVE_ADDITIONAL_INFO = "insert into orderadditionalinfo(info_details,payment_for_violation) values(?,?)";
    private final String SQL_GET_ADDITIONAL_INFO_BY_ORDER_ID = "select * from orderadditionalinfo where order_id=?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderAdditionalInfoDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<OrderAdditionalInfo> findAdditionalInfoByOrderId(Long orderId) {
        return jdbcTemplate.query(SQL_GET_ADDITIONAL_INFO_BY_ORDER_ID,new OrderAdditionalInfoMapper(),orderId);
    }

    @Override
    public List<OrderAdditionalInfo> findInfoByPayment(int price) {
        return jdbcTemplate.query(SQL_FIND_ADDITIONAL_INFO_BY_PAYMENT,new OrderAdditionalInfoMapper(),price);
    }

    @Override
    public OrderAdditionalInfo getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_GET_ADDITIONAL_INFO_BY_ID,new OrderAdditionalInfoMapper(),id);
    }

    @Override
    public List<OrderAdditionalInfo> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL,new OrderAdditionalInfoMapper());
    }

    @Override
    public Long delete(OrderAdditionalInfo info) {
        return (long) jdbcTemplate.update(SQL_DELETE_ADDITIONAL_INFO,info.getId());
    }

    @Override
    public Long update(OrderAdditionalInfo info) {
        return (long) jdbcTemplate.update(SQL_UPDATE_ADDITIONAL_INFO,info.getInfoDetails(),info.getPaymentForViolation(),info.getId());
    }

    @Override
    public Long save(OrderAdditionalInfo info) {
        return (long) jdbcTemplate.update(SQL_SAVE_ADDITIONAL_INFO,info.getInfoDetails(),info.getPaymentForViolation());
    }
}
