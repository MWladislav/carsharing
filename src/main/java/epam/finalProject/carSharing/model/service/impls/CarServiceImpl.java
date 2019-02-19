package epam.finalProject.carSharing.model.service.impls;


import epam.finalProject.carSharing.model.dao.interfaces.CarDao;
import epam.finalProject.carSharing.model.dao.interfaces.CarImageDao;
import epam.finalProject.carSharing.model.dao.interfaces.OrderDao;
import epam.finalProject.carSharing.model.domain.entity.Car;
import epam.finalProject.carSharing.model.domain.entity.CarImage;
import epam.finalProject.carSharing.model.domain.entity.Order;
import epam.finalProject.carSharing.model.service.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CarImageDao carImageDao;

    @Override
    public Car findByManufacturer(String manufacturer) {
        return null;
    }

    @Override
    public Car getCarWithOrders(long id) {
        if (id>0){
            Car car=carDao.getById(id);
            List<CarImage> images=carImageDao.findCarImageByCarId(id);
            car.setImages(images);
            List<Order> orders=orderDao.findOrdersByCarId(id);
            car.setOrders(orders);
            return car;
        }
        return null;
    }

    @Override
    public List<Car> getCarsByAvailable(boolean available) {
        return carDao.getCarsByAvailable(available);
    }

    @Override
    public Car getById(Long id) {
        if (id>0){
            Car car=carDao.getById(id);
            List<CarImage> images=carImageDao.findCarImageByCarId(id);
            car.setImages(images);
            return car;
        }
        return null;
    }

    @Override
    public List<Car> getAll() {
        List<Car> cars=carDao.getAll();
        for (Car car:cars) {
            car.setImages(carImageDao.findCarImageByCarId(car.getId()));
        }
        return cars;
    }

    @Override
    public Long delete(Car car) {
        if (car!=null && car.getId()>0)
            return carDao.delete(car);
        return 0L;
    }

    @Override
    public Long update(Car car) {
        if (car!=null && car.getId()>0 && car.getBodyType()!=null && car.getEngineType()!= null
                && /*StringUtils.isNullOrEmpty(car.getManufacturer()) && StringUtils.isNullOrEmpty(car.getModel()) &&*/ car.getYearOfIssue()>1990)
            return carDao.update(car);
        return 0L;
    }

    @Override
    public Long updateCarAvailable(Car car) {
        if (car!=null && car.isAvailable())
            return carDao.updateCarAvailable(car);
        return null;
    }

    @Override
    public Long save(Car car) {
        if (car!=null && car.getId()>0 && car.getBodyType()!=null && car.getEngineType()!= null
                && /*StringUtils.isNullOrEmpty(car.getManufacturer()) && StringUtils.isNullOrEmpty(car.getModel()) &&*/ car.getYearOfIssue()>1990)
            return carDao.save(car);
        return 0L;
    }
}
