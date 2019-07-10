package epam.training.finalproject.model.domain.mapper;

import epam.training.finalproject.model.domain.entity.User;
import epam.training.finalproject.model.domain.entity.enums.RoleName;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("idUser"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setActive(resultSet.getInt("active")==1);
        return user;
    }
}
