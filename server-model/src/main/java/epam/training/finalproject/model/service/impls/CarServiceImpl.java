package epam.training.finalproject.model.service.impls;

import epam.training.finalproject.exception.EntityNotFoundException;
import epam.training.finalproject.exception.OperationException;
import epam.training.finalproject.model.dao.interfaces.CarDao;
import epam.training.finalproject.model.domain.entity.Car;
import epam.training.finalproject.model.service.interfaces.CarService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

public class CarServiceImpl implements CarService {

    private static final Logger LOGGER = Logger.getLogger(CarServiceImpl.class);

    @Autowired
    private CarDao carDao;

    //TODO add regex to reg number
    @Override
    public Car findCarByRegistrationNumber(String number) {
        if (number != null && !StringUtils.isEmpty(number)) {
            Car car = carDao.findByRegistrationNumber(number).orElseThrow(() -> {
                LOGGER.debug("Car is null!");
                throw new EntityNotFoundException("Car with registration number" + number + " is not found");
            });
            if (!car.isAvailable()) {
                LOGGER.debug("Car is not available!");
                throw new EntityNotFoundException("Car with registration number" + number + " is not found");
            }
            return car;
        }
        LOGGER.debug("Registration number is invalid!");
        throw new IllegalArgumentException("Registration number is invalid");
    }

    @Override
    public Long updateCarAvailable(Car car) {
        if (car != null){
            Long result = carDao.updateCarAvailable(car);
            if (result == -1L){
                LOGGER.debug("Car credentials is invalid!");
                throw new OperationException("Car with id " + car.getId() + " has invalid credentials");
            }
            return result;
        }
        LOGGER.debug("Car is null!");
        throw new IllegalArgumentException("Car is invalid");
    }

    @Override
    public List<Car> getCarsByAvailable(boolean available) {
        List<Car> cars = carDao.getCarsByAvailable(available);
        if (cars.isEmpty()){
            LOGGER.debug("Cars is empty!");
            throw new EntityNotFoundException("Cars is not found");
        }
        return cars;
    }

    @Override
    public Car getById(Long id) {
        if (id > 0){
            Car car = carDao.getById(id).orElseThrow(() -> {
                LOGGER.debug("Car is null!");
                throw new EntityNotFoundException("Car with id" + id + " is not found");
            });
            if (!car.isAvailable()){
                LOGGER.debug("Car is not available!");
                throw new EntityNotFoundException("Car with id" + id + " is not found");
            }
            return car;
        }
        LOGGER.debug("Car id is invalid!");
        throw new IllegalArgumentException("Car id is invalid");
    }

    @Override
    public List<Car> getAll() {
        List<Car> cars = carDao.getAll();
        if (cars.isEmpty()){
            LOGGER.debug("Cars is empty!");
            throw new EntityNotFoundException("Cars is not found");
        }
        return cars;
    }

    @Override
    public Long delete(Car car) {
        if (car != null) {
            car.setDeleted(true);
            car.setAvailable(false);

            Long result = carDao.delete(car);
            if (result == -1L){
                LOGGER.debug("Invalid car credentials!");
                throw new OperationException("Car with id " + car.getId() + " and registration number " + car.getRegistrationNumber() +
                        " has invalid credentials");
            }
            return result;
        }
        LOGGER.debug("delete operation: such car doesn't exist!");
        throw new IllegalArgumentException("Car doesn't exist");
    }

    @Override
    public Long update(Car car) {
        if (car != null) {
            Long result = carDao.update(car);
            if (result == -1L){
                LOGGER.debug("Invalid car credentials!");
                throw new OperationException("Car with id " + car.getId() + " and registration number " + car.getRegistrationNumber() +
                        " has invalid credentials");
            }
            return result;
        }
        LOGGER.debug("update operation: such car doesn't exist!");
        throw new IllegalArgumentException("Car doesn't exist");
    }

    @Override
    public Long save(Car car) {
        if (car != null) {
            Long result = carDao.save(car);
            if (result == -1L){
                LOGGER.debug("Invalid car credentials!");
                throw new OperationException("Car with id " + car.getId() + " and registration number " + car.getRegistrationNumber() +
                        " has invalid credentials");
            }
            return result;
        }
        LOGGER.debug("save operation: such car doesn't exist!");
        throw new IllegalArgumentException("Car doesn't exist");
    }
}
