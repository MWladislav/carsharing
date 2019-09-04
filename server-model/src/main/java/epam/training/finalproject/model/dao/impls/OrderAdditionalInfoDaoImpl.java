package epam.training.finalproject.model.dao.impls;

import epam.training.finalproject.model.dao.interfaces.OrderAdditionalInfoDao;
import epam.training.finalproject.model.domain.entity.OrderAdditionalInfo;
import epam.training.finalproject.model.domain.mapper.OrderAdditionalInfoMapper;
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
public class OrderAdditionalInfoDaoImpl implements OrderAdditionalInfoDao {

    private static Logger LOGGER= Logger.getLogger(OrderAdditionalInfoDaoImpl.class);

    private final String SQL_GET_ALL = "select * from orderadditionalinfo";
    private final String SQL_FIND_ADDITIONAL_INFO_BY_PAYMENT ="select * from orderadditionalinfo where payment_for_violation=?";
    private final String SQL_GET_ADDITIONAL_INFO_BY_ID = "select * from orderadditionalinfo where idOrderAdditionalInfo=?";
    private final String SQL_DELETE_ADDITIONAL_INFO = "delete from orderadditionalinfo where idOrderAdditionalInfo = ?";
    private final String SQL_UPDATE_ADDITIONAL_INFO = "update orderadditionalinfo set info_details=?, payment_for_violation=? where idOrderAdditionalInfo = ?";
    private final String SQL_SAVE_ADDITIONAL_INFO = "insert into orderadditionalinfo(info_details,payment_for_violation) values(?,?)";
    private final String SQL_GET_ADDITIONAL_INFO_BY_ORDER_ID = "select * from orderadditionalinfo where order_id=?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderAdditionalInfoDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<OrderAdditionalInfo> findAdditionalInfoByOrderId(Long orderId) {
        try {
            return jdbcTemplate.query(SQL_GET_ADDITIONAL_INFO_BY_ORDER_ID,new OrderAdditionalInfoMapper(),orderId);
        }
        catch (DataAccessException ex){
            LOGGER.error("Any order additional info with order id "+orderId+" are not found",ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public List<OrderAdditionalInfo> findInfoByPayment(int price) {
        try {
            return jdbcTemplate.query(SQL_FIND_ADDITIONAL_INFO_BY_PAYMENT,new OrderAdditionalInfoMapper(),price);
        }
        catch (DataAccessException ex){
            LOGGER.error("Any order additional info with payment value "+price+" are not found",ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<OrderAdditionalInfo> getById(Long id) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_GET_ADDITIONAL_INFO_BY_ID,new OrderAdditionalInfoMapper(),id)));
        }
        catch (DataAccessException ex){
            LOGGER.error("Order additional info with id "+id+" is not found",ex.getCause());
            return Optional.empty();
        }
    }

    @Override
    public List<OrderAdditionalInfo> getAll() {
        try {
            return jdbcTemplate.query(SQL_GET_ALL,new OrderAdditionalInfoMapper());
        }
        catch (DataAccessException ex){
            LOGGER.error("Any order additional info are not found",ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public Long delete(OrderAdditionalInfo info) {
        try {
            return (long) jdbcTemplate.update(SQL_DELETE_ADDITIONAL_INFO,info.getId());
        }
        catch (DataAccessException ex){
            LOGGER.error("Order additional info with id "+info.getId()+" has invalid credentials",ex.getCause());
            return -1L;
        }
    }

    @Override
    public Long update(OrderAdditionalInfo info) {
        try {
            return (long) jdbcTemplate.update(SQL_UPDATE_ADDITIONAL_INFO,info.getInfoDetails(),info.getPaymentForViolation(),info.getId());
        }
        catch (DataAccessException ex){
            LOGGER.error("Order additional info with id "+info.getId()+" has invalid credentials",ex.getCause());
            return -1L;
        }
    }

    @Override
    public Long save(OrderAdditionalInfo info) {
        try {
            return (long) jdbcTemplate.update(SQL_SAVE_ADDITIONAL_INFO,info.getInfoDetails(),info.getPaymentForViolation());
        }
        catch (DataAccessException ex){
            LOGGER.error("Order additional info has invalid credentials",ex.getCause());
            return -1L;
        }
    }
}
