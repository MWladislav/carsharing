package epam.training.finalproject.model.service.impls;


import epam.training.finalproject.model.dao.interfaces.CarProfileDao;
import epam.training.finalproject.model.dao.interfaces.CarImageDao;
import epam.training.finalproject.model.dao.interfaces.OrderDao;
import epam.training.finalproject.model.domain.entity.CarProfile;
import epam.training.finalproject.model.domain.entity.CarImage;
import epam.training.finalproject.model.service.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarProfileDao carProfileDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CarImageDao carImageDao;

    @Override
    public CarProfile findByManufacturer(String manufacturer) {
        return null;
    }

    //CHANGE THIS!
    @Override
    public List<CarProfile> getCarsByAvailable(boolean available) {
        return null;//carProfileDao.getCarsByAvailable(available);
    }

    @Override
    public CarProfile getById(Long id) {
        if (id>0){
            CarProfile carProfile = carProfileDao.getById(id).get();
            List<CarImage> images=carImageDao.findCarImageByCarId(id);
            carProfile.setImages(images);
            return carProfile;
        }
        return null;
    }

    @Override
    public List<CarProfile> getAll() {
        List<CarProfile> carProfiles = carProfileDao.getAll();
        for (CarProfile carProfile : carProfiles) {
            carProfile.setImages(carImageDao.findCarImageByCarId(carProfile.getId()));
        }
        return carProfiles;
    }

    @Override
    public Long delete(CarProfile carProfile) {
        if (carProfile !=null && carProfile.getId()>0)
            return carProfileDao.delete(carProfile);
        return 0L;
    }

    @Override
    public Long update(CarProfile carProfile) {
        if (carProfile !=null && carProfile.getId()>0 && carProfile.getBodyType()!=null && carProfile.getEngineType()!= null
                && /*StringUtils.isNullOrEmpty(carProfile.getManufacturer()) && StringUtils.isNullOrEmpty(carProfile.getModel()) &&*/ carProfile.getYearOfIssue()>1990)
            return carProfileDao.update(carProfile);
        return 0L;
    }

    //CHANGE THIS!
    @Override
    public Long updateCarAvailable(CarProfile carProfile) {
//        if (carProfile !=null && carProfile.isAvailable())
//            return carProfileDao.updateCarAvailable(carProfile);
        return null;
    }

    @Override
    public Long save(CarProfile carProfile) {
        if (carProfile !=null && carProfile.getId()>0 && carProfile.getBodyType()!=null && carProfile.getEngineType()!= null
                && /*StringUtils.isNullOrEmpty(carProfile.getManufacturer()) && StringUtils.isNullOrEmpty(carProfile.getModel()) &&*/ carProfile.getYearOfIssue()>1990)
            return carProfileDao.save(carProfile);
        return 0L;
    }
}
