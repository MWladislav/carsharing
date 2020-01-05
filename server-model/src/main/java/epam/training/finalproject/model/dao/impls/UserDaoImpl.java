package epam.training.finalproject.model.dao.impls;

import epam.training.finalproject.exception.DuplicateEntityCredentialsException;
import epam.training.finalproject.model.dao.interfaces.UserDao;
import epam.training.finalproject.model.domain.entity.User;
import epam.training.finalproject.model.domain.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private static Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    private final String SQL_GET_ALL = "select * from users";
    private final String SQL_FIND_BY_USERNAME = "select u.idUser, u.username, u.password, u.email, u.is_active from users u where u.username=?";
    private final String SQL_FIND_BY_EMAIL = "select u.idUser, u.username, u.password, u.email, u.is_active from users u where u.email=?";
    private final String SQL_GET_USER_BY_ID = "select u.idUser, u.username, u.password, u.email, u.is_active from users u where u.idUser=?";
    private final String SQL_DELETE_USER = "update users set deleted = ?, is_active = ? where idUser = ?";
    private final String SQL_UPDATE_USER = "update users set username = ?, password = ?,email = ? where idUser = ?";
    private final String SQL_BAN_USER = "update users set is_active = ? where idUser = ?";
    private final String SQL_SAVE_USER = "insert into users(username,password,email,is_active) values(?,?,?,?)";
    private final String SQL_EXIST_USER_BY_EMAIL = "select count(email) from users where email = ?";
    private final String SQL_EXIST_USER_BY_USERNAME = "select count(username) from users where username = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
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

    @Override
    public boolean existsByEmail(String email) {
        Boolean query = jdbcTemplate.query(SQL_EXIST_USER_BY_EMAIL, new ResultSetExtractor<Boolean>() {
                    @Override
                    public Boolean extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        return resultSet.getLong(0) == 1;
                    }
                }
                , email);
        return query != null ? query : false;
    }

    public Optional<User> findByUsername(String username) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_FIND_BY_USERNAME, new UserMapper(), username)));
        } catch (DataAccessException ex) {
            LOGGER.error("User with username " + username + " is not found", ex.getCause());
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new UserMapper(), email)));
        } catch (DataAccessException ex) {
            LOGGER.error("User with email " + email + " is not found", ex.getCause());
            return Optional.empty();
        }
    }

    public Optional<User> getById(Long id) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_GET_USER_BY_ID, new UserMapper(), id)));
        } catch (DataAccessException ex) {
            LOGGER.error("User with id " + id + " is not found", ex.getCause());
            return Optional.empty();
        }

    }

    public List<User> getAll() {
        try {
            return jdbcTemplate.query(SQL_GET_ALL, new UserMapper());
        } catch (DataAccessException ex) {
            LOGGER.error("Users are not found", ex.getCause());
            return Collections.emptyList();
        }
    }

    public Long update(User user) {
        try {
            if (findByUsername(user.getUsername()).isPresent())
                throw new DuplicateEntityCredentialsException("Username " + user.getUsername() + " is already taken");
            if (findByEmail(user.getEmail()).isPresent())
                throw new DuplicateEntityCredentialsException("Email " + user.getEmail() + " is already taken");
            return (long) jdbcTemplate.update(SQL_UPDATE_USER, user.getUsername(), user.getPassword(), user.getEmail(), user.getId());
        } catch (DataAccessException ex) {
            LOGGER.error("User with id " + user.getId() + " and username " + user.getUsername() + " has invalid credentials", ex.getCause());
            return -1L;
        }
    }

    public Long banUser(User user) {
        try {
            return (long) jdbcTemplate.update(SQL_BAN_USER, user.isActive(), user.getId());
        } catch (DataAccessException ex) {
            LOGGER.error("User with id " + user.getId() + " has invalid credentials", ex.getCause());
            return -1L;
        }
    }

    public Long delete(User user) {
        try {
            return (long) jdbcTemplate.update(SQL_DELETE_USER, user.isDeleted(), user.isActive(), user.getId());
        } catch (DataAccessException ex) {
            LOGGER.error("User with id " + user.getId() + " has invalid credentials", ex.getCause());
            return -1L;
        }
    }

    public Long save(User user) {
        try {
            return (long) jdbcTemplate.update(SQL_SAVE_USER, user.getUsername(), user.getPassword(), user.getEmail(),
                    user.isActive());
        } catch (DataAccessException ex) {
            LOGGER.error("User with username " + user.getUsername() + " has invalid credentials", ex.getCause());
            return -1L;
        }
    }
}
