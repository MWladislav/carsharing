package epam.training.finalproject.model.domain.mapper;

import epam.training.finalproject.model.domain.entity.CarProfile;
import epam.training.finalproject.model.domain.entity.enums.CarBodyType;
import epam.training.finalproject.model.domain.entity.enums.CarEngineType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarProfileMapper implements RowMapper<CarProfile> {
    @Override
    public CarProfile mapRow(ResultSet resultSet, int i) throws SQLException {
        CarProfile carProfile = new CarProfile();
        carProfile.setId(resultSet.getInt("id"));
        carProfile.setManufacturer(resultSet.getString("manufacturer"));
        carProfile.setModel(resultSet.getString("model"));
        carProfile.setBodyType(CarBodyType.valueOf(resultSet.getString("body_type").toUpperCase()));
        carProfile.setEngineType(CarEngineType.valueOf(resultSet.getString("engine_type").toUpperCase()));
        carProfile.setYearOfIssue(resultSet.getInt("year_of_issue"));
        carProfile.setDeleted(resultSet.getInt("deleted")==1);
        return carProfile;
    }
}
