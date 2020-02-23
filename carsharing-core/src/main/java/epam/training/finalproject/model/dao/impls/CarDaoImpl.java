package epam.training.finalproject.model.dao.impls;

import epam.training.finalproject.model.dao.interfaces.CarDao;
import epam.training.finalproject.model.domain.entity.Car;
import epam.training.finalproject.model.domain.mapper.CarMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CarDaoImpl implements CarDao {

    private static Logger LOGGER = Logger.getLogger(CarDaoImpl.class);

    private final String SQL_GET_ALL = "select * from cars";
    private final String SQL_GET_CARS_BY_AVAILABLE = "select * from cars where available=?";
    private final String SQL_GET_CAR_BY_ID = "select * from cars where idCar=?";
    private final String SQL_GET_CAR_BY_REGISTRATION_NUMBER = "select * from cars where registration_number=?";
    private final String SQL_DELETE_CAR = "update cars set deleted=?, available=? where idCar = ?";
    private final String SQL_UPDATE_CAR = "update cars set available=?, registration_number=? where idCar = ?";
    private final String SQL_UPDATE_CAR_AVAILABLE = "update cars set available=? where idCar = ?";
    private final String SQL_SAVE_CAR = "insert into cars(available,registration_number,car_profile_id) values (?,?,?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Car> findByRegistrationNumber(String registrationNumber) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_GET_CAR_BY_REGISTRATION_NUMBER,
                    new CarMapper(), registrationNumber)));
        } catch (DataAccessException ex) {
            LOGGER.error("Car with registration number " + registrationNumber + " is not found", ex.getCause());
            return Optional.empty();
        }
    }

    @Override
    public Long updateCarAvailable(Car car) {
        try {
            return (long) jdbcTemplate.update(SQL_UPDATE_CAR_AVAILABLE, car.isAvailable(), car.getId());
        } catch (DataAccessException ex) {
            LOGGER.error("Car with id " + car.getId() + " has invalid credentials", ex.getCause());
            return -1L;
        }
    }

    @Override
    public List<Car> getCarsByAvailable(boolean available) {
        try {
            return jdbcTemplate.query(SQL_GET_CARS_BY_AVAILABLE, new CarMapper(), available);
        } catch (DataAccessException ex) {
            LOGGER.error("Any cars which are " + (available ? "available" : "not available") + " are not found",
                    ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Car> getById(Long id) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_GET_CAR_BY_ID, new CarMapper(), id)));
        } catch (DataAccessException ex) {
            LOGGER.error("CarProfile with id " + id + " is not found", ex.getCause());
            return Optional.empty();
        }
    }

    @Override
    public List<Car> getAll() {
        try {
            return jdbcTemplate.query(SQL_GET_ALL, new CarMapper());
        } catch (DataAccessException ex) {
            LOGGER.error("Any cars are not found", ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public Long delete(Car car) {
        try {
            return (long) jdbcTemplate.update(SQL_DELETE_CAR, car.isDeleted(), car.isAvailable(), car.getId());
        } catch (DataAccessException ex) {
            LOGGER.error("Car with id " + car.getId() + " has invalid credentials", ex.getCause());
            return -1L;
        }
    }

    @Override
    public Long update(Car car) {
        try {
            return (long) jdbcTemplate.update(SQL_UPDATE_CAR, car.isAvailable(), car.getRegistrationNumber(), car.getId());
        } catch (DataAccessException ex) {
            LOGGER.error("CarProfile with id " + car.getId() + " has invalid credentials", ex.getCause());
            return -1L;
        }
    }

    @Override
    public Long save(Car car) {
        try {
            return (long) jdbcTemplate.update(SQL_SAVE_CAR, car.isAvailable(), car.getRegistrationNumber(),
                    car.getCarProfile().getId());
        } catch (DataAccessException ex) {
            LOGGER.error("CarProfile has invalid credentials", ex.getCause());
            return -1L;
        }
    }
}
