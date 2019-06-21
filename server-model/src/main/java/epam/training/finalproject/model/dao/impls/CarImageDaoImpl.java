package epam.training.finalproject.model.dao.impls;


import epam.training.finalproject.model.dao.interfaces.CarImageDao;
import epam.training.finalproject.model.domain.entity.CarImage;
import epam.training.finalproject.model.domain.mapper.CarImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CarImageDaoImpl implements CarImageDao {


    private final String SQL_GET_ALL = "select * from carimage";
    private final String SQL_GET_CAR_IMAGE_BY_ID = "select * from carimage where idCarImage=?";
    private final String SQL_GET_CAR_IMAGE_BY_CAR_ID = "select * from carimage where car_id=?";
    private final String SQL_DELETE_CAR_IMAGE = "delete from carimage where idCarImage = ?";
    private final String SQL_UPDATE_CAR_IMAGE = "update carimage set path=?, car_id=? where idCarImage = ?";
    private final String SQL_SAVE_CAR_IMAGE = "insert into carimage(path, car_id) values (?,?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarImageDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public CarImage getById(Long id) {
        return jdbcTemplate.queryForObject(SQL_GET_CAR_IMAGE_BY_ID,new CarImageMapper(), id);
    }

    @Override
    public List<CarImage> findCarImageByCarId(long carId) {
        return jdbcTemplate.query(SQL_GET_CAR_IMAGE_BY_CAR_ID,new CarImageMapper(),carId);
    }

    @Override
    public List<CarImage> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL,new CarImageMapper());
    }

    @Override
    public Long delete(CarImage carImage) {
        return (long) jdbcTemplate.update(SQL_DELETE_CAR_IMAGE, carImage.getId());
    }

    @Override
    public Long update(CarImage carImage) {
        return (long) jdbcTemplate.update(SQL_UPDATE_CAR_IMAGE, carImage.getImagePath(),carImage.getCarId(),carImage.getId());
    }

    @Override
    public Long save(CarImage carImage) {
        return (long) jdbcTemplate.update(SQL_SAVE_CAR_IMAGE, carImage.getImagePath(),carImage.getCarId());
    }
}
