package epam.training.finalproject.model.dao.impls;


import epam.training.finalproject.model.dao.interfaces.CarImageDao;
import epam.training.finalproject.model.domain.entity.CarImage;
import epam.training.finalproject.model.domain.mapper.CarImageMapper;
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
public class CarImageDaoImpl implements CarImageDao {

    private static Logger LOGGER = Logger.getLogger(CarImageDaoImpl.class);

    private final String SQL_GET_ALL = "select * from car_images";
    private final String SQL_GET_CAR_IMAGE_BY_ID = "select * from car_images where idCarImage=?";
    private final String SQL_GET_MAIN_CAR_IMAGE_BY_CAR_PROFILE_ID = "select * from car_images where image_url like '%main%' and idCarImage=?";
    private final String SQL_GET_CAR_IMAGES_BY_CAR_PROFILE_ID = "select * from car_images where car_profile_id=?";
    private final String SQL_DELETE_CAR_IMAGE = "update car_images set deleted=? where idCarImage = ?";
    private final String SQL_UPDATE_CAR_IMAGE = "update car_images set image_url=?, car_profile_id=? where idCarImage = ?";
    private final String SQL_SAVE_CAR_IMAGE = "insert into car_images(image_url, car_profile_id) values (?,?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarImageDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Optional<CarImage> getById(Long id) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(SQL_GET_CAR_IMAGE_BY_ID, new CarImageMapper(), id)));
        } catch (DataAccessException ex) {
            LOGGER.debug("CarProfile image with id " + id + " is not found", ex.getCause());
            return Optional.empty();
        }
    }

    @Override
    public List<CarImage> findCarImagesByCarProfileId(Long carProfileId) {
        try {
            return jdbcTemplate.query(SQL_GET_CAR_IMAGES_BY_CAR_PROFILE_ID, new CarImageMapper(), carProfileId);
        } catch (DataAccessException ex) {
            LOGGER.debug("Any car images with car profile id " + carProfileId + " are not found", ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<CarImage> getMainCarImageByCarProfileId(Long carProfileId) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject
                    (SQL_GET_MAIN_CAR_IMAGE_BY_CAR_PROFILE_ID, new CarImageMapper(), carProfileId)));
        } catch (DataAccessException ex) {
            LOGGER.debug("Main car image with car profile id " + carProfileId + " is not found", ex.getCause());
            return Optional.empty();
        }
    }

    @Override
    public List<CarImage> getAll() {
        try {
            return jdbcTemplate.query(SQL_GET_ALL, new CarImageMapper());
        } catch (DataAccessException ex) {
            LOGGER.debug("Any car images are not found", ex.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public Long delete(CarImage carImage) {
        try {
            return (long) jdbcTemplate.update(SQL_DELETE_CAR_IMAGE,carImage.isDeleted(), carImage.getId());
        } catch (DataAccessException ex) {
            LOGGER.debug("CarProfile image with id " + carImage.getId() + " has invalid credentials", ex.getCause());
            return -1L;
        }
    }

    @Override
    public Long update(CarImage carImage) {
        try {
            return (long) jdbcTemplate.update(SQL_UPDATE_CAR_IMAGE, carImage.getImageUrl(), carImage.getCarProfileId(), carImage.getId());
        } catch (DataAccessException ex) {
            LOGGER.debug("CarProfile image with id " + carImage.getId() + " has invalid credentials", ex.getCause());
            return -1L;
        }
    }

    @Override
    public Long save(CarImage carImage) {
        try {
            return (long) jdbcTemplate.update(SQL_SAVE_CAR_IMAGE, carImage.getImageUrl(), carImage.getCarProfileId());
        } catch (DataAccessException ex) {
            LOGGER.debug("CarProfile image has invalid credentials", ex.getCause());
            return -1L;
        }

    }
}
