package epam.training.finalproject.carsharing.model.dao.impls;

import epam.training.finalproject.carsharing.model.dao.interfaces.UserDao;
import epam.training.finalproject.carsharing.model.domain.entity.PassportData;
import epam.training.finalproject.carsharing.model.domain.entity.User;
import epam.training.finalproject.carsharing.model.domain.mapper.PassportDataMapper;
import epam.training.finalproject.carsharing.model.domain.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final String SQL_GET_ALL = "select u.*, r.role_name from user u left join role r on u.role_idRole = r.idRole";
    private final String SQL_GET_BY_ID_WITH_PASSPORT_DATA = "select u.idUser, u.username, u.password, u.email, u.active, " +
            "pd.idPassportData, pd.first_name, pd.last_name, pd.patronymic, pd.birth_date, pd.gender, pd.passport_number, pd.identification_number, " +
            "a.*, r.name from user u " +
            "left join passportdata pd on u.passportdata_idPassportData=pd.idPassportData " +
            "left join address a on pd.address_idAddress = a.idAddress " +
            "left join role r on u.role_idRole = r.idRole " +
            "where u.idUser=?";
    private final String SQL_FIND_BY_USERNAME = "select u.idUser, u.username, u.password, u.email, u.active, " +
            "r.role_name from user u left join role r on u.role_idRole = r.idRole where u.username=?";
    private final String SQL_GET_USER_BY_ID = "select u.idUser, u.username, u.password, u.email, u.active, " +
            "r.role_name from user u " +
            "left join role r on u.role_idRole = r.idRole " +
            "where u.idUser=?";
    private final String SQL_DELETE_USER = "delete from user where idUser = ?";
    private final String SQL_UPDATE_USER = "update user set username=?, password=?,email=? where idUser = ?";
    private final String SQL_SAVE_USER = "insert into user(username,password,email,role_idRole,active) values(?,?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User getByIdWithPassportData(Long id) {
        return jdbcTemplate.queryForObject(SQL_GET_BY_ID_WITH_PASSPORT_DATA, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new UserMapper().mapRow(resultSet, i);
                PassportData passportData = new PassportDataMapper().mapRow(resultSet,i);
                user.setPassportData(passportData);
                return user;
            }
        }, id);
    }

    public User findByUsername(String username) throws DataAccessException {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_USERNAME,new UserMapper(),username);
    }

    public User getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_GET_USER_BY_ID,new UserMapper(),id);
    }

    public List<User> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL,new UserMapper());
    }

    public Long update(User user) {
        return (long) jdbcTemplate.update(SQL_UPDATE_USER,user.getUsername(),user.getPassword(),user.getEmail(),user.getId());
    }

    public Long delete(User user) {
        return (long) jdbcTemplate.update(SQL_DELETE_USER,user.getId());
    }

    public Long save(User user) {
        return (long) jdbcTemplate.update(SQL_SAVE_USER,user.getUsername(),user.getPassword(),user.getEmail(),user.getRole().ordinal()+1,user.isActive());
    }
}
