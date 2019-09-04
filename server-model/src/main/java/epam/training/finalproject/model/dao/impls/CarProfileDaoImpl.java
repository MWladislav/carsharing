package epam.training.finalproject.model.dao.impls;

import epam.training.finalproject.model.dao.interfaces.CarProfileDao;
import epam.training.finalproject.model.domain.entity.CarProfile;
import epam.training.finalproject.model.domain.entity.enums.CarBodyType;
import epam.training.finalproject.model.domain.entity.enums.CarEngineType;
import epam.training.finalproject.model.domain.mapper.CarMapper;
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

    private static Logger LOGGER= Logger.getLogger(CarProfileDaoImpl.class);

    private final String SQL_GET_ALL = "select * from car_profile";
    private final String SQL_GET_CAR_BY_ID = "select * from car_profile where id_car_profile=?";
    private final String SQL_DELETE_CAR = "update car_profile set is_deleted=1 where id_car_profile = ?";
    private final String SQL_UPDATE_CAR = "update car_profile set manufacturer=?, model=?, body_type=?, engine_type=?, year_of_issue=? where id_car_profile = ?";
    private final String SQL_SAVE_CAR = "insert into car_profile(manufacturer,model,body_type,engine_type,year_of_issue) values (?,?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarProfileDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<CarProfile> findByManufacturer(String manufacturer) {
        return null;
    }

    @Override
    public List<CarProfile> findByModel(String model) {
        return null;
    }

    @Override
    public List<CarProfile> findByBodyType(CarBodyType bodyType) {
        return null;
    }

    @Override
    public List<CarProfile> findByEngineType(CarEngineType engineType) {
        return null;
    }

    @Override
    public Optional<CarProfile> getById(Long id) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_GET_CAR_BY_ID, new CarProfileMapper(), id)));
        }
        catch (DataAccessException ex){
            LOGGER.error("CarProfile with id "+id+" is not found",ex.getCause());
            return Optional.empty();
        }
    }

    @Override
    public List<CarProfile> getAll() {
        try {
            return jdbcTemplate.query(SQL_GET_ALL,new CarProfileMapper());
        }
        catch (DataAccessException ex){
            LOGGER.error("Any car profiles are not found",ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public Long delete(CarProfile carProfile) {
        try {
            return (long) jdbcTemplate.update(SQL_DELETE_CAR, carProfile.getId());
        }
        catch (DataAccessException ex){
            LOGGER.error("CarProfile with id "+ carProfile.getId()+" has invalid credentials",ex.getCause());
            return -1L;
        }
    }

    @Override
    public Long update(CarProfile carProfile) {
        try {
            return (long) jdbcTemplate.update(SQL_UPDATE_CAR, carProfile.getManufacturer(), carProfile.getModel(),
                    carProfile.getBodyType(), carProfile.getEngineType(), carProfile.getYearOfIssue(), carProfile.getId());
        }
        catch (DataAccessException ex){
            LOGGER.error("CarProfile with id "+ carProfile.getId()+" has invalid credentials",ex.getCause());
            return -1L;
        }
    }

    @Override
    public Long save(CarProfile carProfile) {
        try {
            return (long) jdbcTemplate.update(SQL_SAVE_CAR, carProfile.getManufacturer(), carProfile.getModel(),
                    carProfile.getBodyType(), carProfile.getEngineType(), carProfile.getYearOfIssue());
        }
        catch (DataAccessException ex){
            LOGGER.error("CarProfile has invalid credentials",ex.getCause());
            return -1L;
        }
    }
}
