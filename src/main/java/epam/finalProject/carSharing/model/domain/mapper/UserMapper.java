package epam.finalProject.carSharing.model.domain.mapper;

import epam.finalProject.carSharing.model.domain.entity.PassportData;
import epam.finalProject.carSharing.model.domain.entity.User;
import epam.finalProject.carSharing.model.domain.entity.enums.Role;
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
        user.setRole(Role.valueOf(resultSet.getString("role_name")));
        return user;
    }
}
