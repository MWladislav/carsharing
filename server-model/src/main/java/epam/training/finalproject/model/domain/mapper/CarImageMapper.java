package epam.training.finalproject.model.domain.mapper;

import epam.training.finalproject.model.domain.entity.CarImage;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarImageMapper implements RowMapper<CarImage> {

    @Override
    public CarImage mapRow(ResultSet resultSet, int i) throws SQLException {
        CarImage carImage = new CarImage();
        carImage.setId(resultSet.getInt("idCarImage"));
        carImage.setImagePath(resultSet.getString("path"));
        carImage.setCarProfileId(resultSet.getInt("car_id"));
        carImage.setDeleted(resultSet.getInt("is_deleted")==1);
        return carImage;
    }
}
