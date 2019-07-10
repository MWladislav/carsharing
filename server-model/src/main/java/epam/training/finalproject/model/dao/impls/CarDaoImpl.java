package epam.training.finalproject.model.dao.impls;

import epam.training.finalproject.model.dao.interfaces.CarDao;
import epam.training.finalproject.model.domain.entity.Car;
import epam.training.finalproject.model.domain.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {

    private final String SQL_GET_ALL = "select * from car";
    private final String SQL_GET_CARS_BY_AVAILABLE = "select * from car where available=?";
    private final String SQL_GET_CAR_BY_ID = "select * from car where idCar=?";
    private final String SQL_DELETE_CAR = "delete from car where idCar = ?";
    private final String SQL_UPDATE_CAR = "update car set manufacturer=?, model=?, body_type=?, " +
            "engine_type=?, year_of_issue=?,available=? where idCar = ?";
    private final String SQL_UPDATE_CAR_AVAILABLE = "update car set available=? where idCar = ?";
    private final String SQL_SAVE_CAR = "insert into car(manufacturer,model,body_type,engine_type,year_of_issue,available)" +
            " values (?,?,?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Car getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_GET_CAR_BY_ID,new CarMapper(),id);
    }

    @Override
    public List<Car> getCarsByAvailable(boolean available) {
        return jdbcTemplate.query(SQL_GET_CARS_BY_AVAILABLE,new CarMapper(),available);
    }

    @Override
    public List<Car> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL,new CarMapper());
    }

    @Override
    public Long delete(Car car) {
        return (long) jdbcTemplate.update(SQL_DELETE_CAR,car.getId());
    }

    @Override
    public Long update(Car car) {
        return (long) jdbcTemplate.update(SQL_UPDATE_CAR,car.getManufacturer(),car.getModel(),
                car.getBodyType(),car.getEngineType(),car.getYearOfIssue(),car.isAvailable(),car.getId());
    }

    @Override
    public Long updateCarAvailable(Car car) {
        return (long) jdbcTemplate.update(SQL_UPDATE_CAR_AVAILABLE,car.isAvailable(),car.getId());
    }

    @Override
    public Long save(Car car) {
        return (long) jdbcTemplate.update(SQL_SAVE_CAR,car.getManufacturer(),car.getModel(),
                car.getBodyType(),car.getEngineType(),car.getYearOfIssue(),car.isAvailable());
    }
}
