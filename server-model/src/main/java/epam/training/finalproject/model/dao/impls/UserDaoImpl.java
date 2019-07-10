package epam.training.finalproject.model.dao.impls;

import epam.training.finalproject.model.dao.interfaces.UserDao;
import epam.training.finalproject.model.domain.entity.PassportData;
import epam.training.finalproject.model.domain.entity.User;
import epam.training.finalproject.model.domain.mapper.PassportDataMapper;
import epam.training.finalproject.model.domain.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final String SQL_GET_ALL = "select * from user";
    private final String SQL_FIND_BY_USERNAME = "select u.idUser, u.username, u.password, u.email, u.active " +
            "from user u where u.username=?";
    private final String SQL_GET_USER_BY_ID = "select u.idUser, u.username, u.password, u.email, u.active " +
            "from user u " +
            "where u.idUser=?";
    private final String SQL_DELETE_USER = "delete from user where idUser = ?";
    private final String SQL_UPDATE_USER = "update user set username=?, password=?,email=? where idUser = ?";
    private final String SQL_SAVE_USER = "insert into user(username,password,email,active) values(?,?,?,?)";
    private final String SQL_EXIST_USER_BY_USERNAME = "select count(*) from user where username=?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean existsByUsername(String username) {
        Boolean query = jdbcTemplate.query(SQL_EXIST_USER_BY_USERNAME, new ResultSetExtractor<Boolean>() {
                    @Override
                    public Boolean extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        return resultSet.getLong(0) == 1;
                    }
                }
                , username);
        return query != null ? query : false;
    }

    public User findByUsername(String username) throws DataAccessException {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_USERNAME, new UserMapper(), username);
    }

    public User getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_GET_USER_BY_ID, new UserMapper(), id);
    }

    public List<User> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL, new UserMapper());
    }

    public Long update(User user) {
        return (long) jdbcTemplate.update(SQL_UPDATE_USER, user.getUsername(), user.getPassword(), user.getEmail(), user.getId());
    }

    public Long delete(User user) {
        return (long) jdbcTemplate.update(SQL_DELETE_USER, user.getId());
    }

    public Long save(User user) {
        return (long) jdbcTemplate.update(SQL_SAVE_USER, user.getUsername(), user.getPassword(), user.getEmail(), user.isActive());
    }
}
