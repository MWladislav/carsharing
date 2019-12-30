package epam.training.finalproject.model.service.impls;


import epam.training.finalproject.model.dao.interfaces.CarImageDao;
import epam.training.finalproject.model.dao.interfaces.CarProfileDao;
import epam.training.finalproject.model.domain.entity.CarImage;
import epam.training.finalproject.model.domain.entity.CarProfile;
import epam.training.finalproject.model.domain.entity.enums.CarBodyType;
import epam.training.finalproject.model.domain.entity.enums.CarEngineType;
import epam.training.finalproject.model.service.interfaces.CarProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarProfileServiceImpl implements CarProfileService {

    @Autowired
    private CarProfileDao carProfileDao;

    @Autowired
    private CarImageDao carImageDao;

    @Override
    public CarProfile findByModel(String model) {
        return null;
    }

    @Override
    public CarProfile findByBodyType(CarBodyType bodyType) {
        return null;
    }

    @Override
    public CarProfile findByEngineType(CarEngineType engineType) {
        return null;
    }

    @Override
    public CarProfile findByManufacturer(String manufacturer) {
        return null;
    }


    @Override
    public CarProfile getById(Long id) {
        if (id>0){
            CarProfile carProfile = carProfileDao.getById(id).get();
            List<CarImage> images=carImageDao.findCarImageByCarProfileId(id);
            carProfile.setImages(images);
            return carProfile;
        }
        return null;
    }

    @Override
    public List<CarProfile> getAll() {
        List<CarProfile> carProfiles = carProfileDao.getAll();
        for (CarProfile carProfile : carProfiles) {
            carProfile.setImages(carImageDao.findCarImageByCarProfileId(carProfile.getId()));
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

    @Override
    public Long save(CarProfile carProfile) {
        if (carProfile !=null && carProfile.getId()>0 && carProfile.getBodyType()!=null && carProfile.getEngineType()!= null
                && /*StringUtils.isNullOrEmpty(carProfile.getManufacturer()) && StringUtils.isNullOrEmpty(carProfile.getModel()) &&*/ carProfile.getYearOfIssue()>1990)
            return carProfileDao.save(carProfile);
        return 0L;
    }
}
