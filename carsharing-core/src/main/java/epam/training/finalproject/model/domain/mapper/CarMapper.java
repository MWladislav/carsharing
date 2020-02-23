package epam.training.finalproject.model.domain.mapper;

import epam.training.finalproject.model.domain.entity.Car;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements RowMapper<Car> {
    public Car mapRow(ResultSet resultSet, int i) throws SQLException {
        Car carProfile = new Car();
        carProfile.setId(resultSet.getInt("id"));
        carProfile.setRegistrationNumber(resultSet.getString("registration_number"));
        carProfile.setAvailable(resultSet.getBoolean("available"));
        carProfile.setDeleted(resultSet.getInt("deleted") == 1);
        return carProfile;
    }
}
