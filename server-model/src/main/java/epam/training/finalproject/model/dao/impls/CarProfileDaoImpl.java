package epam.training.finalproject.model.dao.impls;

import epam.training.finalproject.model.dao.interfaces.CarProfileDao;
import epam.training.finalproject.model.domain.entity.CarProfile;
import epam.training.finalproject.model.domain.entity.enums.CarBodyType;
import epam.training.finalproject.model.domain.entity.enums.CarEngineType;
import epam.training.finalproject.model.domain.mapper.CarProfileMapper;
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
public class CarProfileDaoImpl implements CarProfileDao {

    private static Logger LOGGER = Logger.getLogger(CarProfileDaoImpl.class);

    private final String SQL_FIND_BY_MANUFACTURER = "select * from car_profiles where manufacturer=?";
    private final String SQL_FIND_BY_MODEL = "select * from car_profiles where manufacturer=?";
    private final String SQL_FIND_BY_BODY_TYPE = "select * from car_profiles where manufacturer=?";
    private final String SQL_FIND_BY_ENGINE_TYPE = "select * from car_profiles where manufacturer=?";
    private final String SQL_GET_ALL = "select * from car_profiles";
    private final String SQL_GET_CAR_PROFILE_BY_ID = "select * from car_profiles where id_car_profile=?";
    private final String SQL_DELETE_CAR_PROFILE = "update car_profiles set deleted=? where id_car_profile = ?";
    private final String SQL_UPDATE_CAR_PROFILE = "update car_profiles set manufacturer=?, model=?, body_type=?, " +
            "engine_type=?, year_of_issue=? where id_car_profile = ?";
    private final String SQL_SAVE_CAR_PROFILE = "insert into car_profiles(manufacturer,model,body_type,engine_type,year_of_issue)" +
            " values (?,?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarProfileDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<CarProfile> findByManufacturer(String manufacturer) {
        try {
            return jdbcTemplate.query(SQL_FIND_BY_MANUFACTURER, new CarProfileMapper(), manufacturer);
        } catch (DataAccessException ex) {
            LOGGER.error("Any car profiles are not found", ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public List<CarProfile> findByModel(String model) {
        try {
            return jdbcTemplate.query(SQL_FIND_BY_MODEL, new CarProfileMapper(), model);
        } catch (DataAccessException ex) {
            LOGGER.error("Any car profiles are not found", ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public List<CarProfile> findByBodyType(CarBodyType bodyType) {
        try {
            return jdbcTemplate.query(SQL_FIND_BY_BODY_TYPE, new CarProfileMapper(), bodyType);
        } catch (DataAccessException ex) {
            LOGGER.error("Any car profiles are not found", ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public List<CarProfile> findByEngineType(CarEngineType engineType) {
        try {
            return jdbcTemplate.query(SQL_FIND_BY_ENGINE_TYPE, new CarProfileMapper(), engineType);
        } catch (DataAccessException ex) {
            LOGGER.error("Any car profiles are not found", ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<CarProfile> getById(Long id) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_GET_CAR_PROFILE_BY_ID,
                    new CarProfileMapper(), id)));
        } catch (DataAccessException ex) {
            LOGGER.error("CarProfile with id " + id + " is not found", ex.getCause());
            return Optional.empty();
        }
    }

    @Override
    public List<CarProfile> getAll() {
        try {
            return jdbcTemplate.query(SQL_GET_ALL, new CarProfileMapper());
        } catch (DataAccessException ex) {
            LOGGER.error("Any car profiles are not found", ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public Long delete(CarProfile carProfile) {
        try {
            return (long) jdbcTemplate.update(SQL_DELETE_CAR_PROFILE, carProfile.isDeleted(), carProfile.getId());
        } catch (DataAccessException ex) {
            LOGGER.error("CarProfile with id " + carProfile.getId() + " has invalid credentials", ex.getCause());
            return -1L;
        }
    }

    @Override
    public Long update(CarProfile carProfile) {
        try {
            return (long) jdbcTemplate.update(SQL_UPDATE_CAR_PROFILE, carProfile.getManufacturer(), carProfile.getModel(),
                    carProfile.getBodyType(), carProfile.getEngineType(), carProfile.getYearOfIssue(), carProfile.getId());
        } catch (DataAccessException ex) {
            LOGGER.error("CarProfile with id " + carProfile.getId() + " has invalid credentials", ex.getCause());
            return -1L;
        }
    }

    @Override
    public Long save(CarProfile carProfile) {
        try {
            return (long) jdbcTemplate.update(SQL_SAVE_CAR_PROFILE, carProfile.getManufacturer(), carProfile.getModel(),
                    carProfile.getBodyType(), carProfile.getEngineType(), carProfile.getYearOfIssue());
        } catch (DataAccessException ex) {
            LOGGER.error("CarProfile has invalid credentials", ex.getCause());
            return -1L;
        }
    }
}
