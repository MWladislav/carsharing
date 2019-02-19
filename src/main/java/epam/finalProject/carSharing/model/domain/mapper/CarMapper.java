package epam.finalProject.carSharing.model.domain.mapper;

import epam.finalProject.carSharing.model.domain.entity.Car;
import epam.finalProject.carSharing.model.domain.entity.enums.CarBodyType;
import epam.finalProject.carSharing.model.domain.entity.enums.CarEngineType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements RowMapper<Car> {
    public Car mapRow(ResultSet resultSet, int i) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getInt("idCar"));
        car.setManufacturer(resultSet.getString("manufacturer"));
        car.setModel(resultSet.getString("model"));
        car.setBodyType(CarBodyType.valueOf(resultSet.getString("body_type").toUpperCase()));
        car.setEngineType(CarEngineType.valueOf(resultSet.getString("engine_type").toUpperCase()));
        car.setYearOfIssue(resultSet.getInt("year_of_issue"));
        car.setAvailable(resultSet.getBoolean("available"));
        return car;
    }
}
