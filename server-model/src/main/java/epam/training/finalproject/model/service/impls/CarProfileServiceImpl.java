package epam.training.finalproject.model.service.impls;


import epam.training.finalproject.model.dao.interfaces.CarImageDao;
import epam.training.finalproject.model.dao.interfaces.CarProfileDao;
import epam.training.finalproject.model.domain.entity.CarImage;
import epam.training.finalproject.model.domain.entity.CarProfile;
import epam.training.finalproject.model.domain.entity.enums.CarBodyType;
import epam.training.finalproject.model.domain.entity.enums.CarEngineType;
import epam.training.finalproject.model.service.interfaces.CarProfileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CarProfileServiceImpl implements CarProfileService {

    private static Logger LOGGER = Logger.getLogger(CarProfileServiceImpl.class);

    private final int maxYearOfIssue = 1990;

    @Autowired
    private CarProfileDao carProfileDao;

    @Autowired
    private CarImageDao carImageDao;

    @Override
    public List<CarProfile> findByModel(String model) {
        if (model != null && !model.isEmpty()) {
            return getCarProfilesWithMainImage(carProfileDao.findByModel(model));
        }
        LOGGER.debug("Car model is null or empty!");
        return Collections.emptyList();
    }


    @Override
    public List<CarProfile> findByBodyType(CarBodyType bodyType) {
        if (bodyType != null) {
            return getCarProfilesWithMainImage(carProfileDao.findByBodyType(bodyType));
        }
        LOGGER.debug("Car model is null or empty!");
        return Collections.emptyList();
    }

    @Override
    public List<CarProfile> findByEngineType(CarEngineType engineType) {
        if (engineType != null) {
            return getCarProfilesWithMainImage(carProfileDao.findByEngineType(engineType));
        }
        LOGGER.debug("Car model is null or empty!");
        return Collections.emptyList();
    }

    @Override
    public List<CarProfile> findByYearOfIssue(int yearOfIssue) {
        if (yearOfIssue >= maxYearOfIssue) {
            return getCarProfilesWithMainImage(carProfileDao.findByYearOfIssue(yearOfIssue));
        }
        LOGGER.debug("Car model is null or empty!");
        return Collections.emptyList();
    }

    @Override
    public List<CarProfile> findByManufacturer(String manufacturer) {
        if (manufacturer != null && !manufacturer.isEmpty()) {
            return getCarProfilesWithMainImage(carProfileDao.findByManufacturer(manufacturer));
        }
        LOGGER.debug("Car model is null or empty!");
        return Collections.emptyList();
    }


    @Override
    public CarProfile getById(Long id) {
        if (id > 0) {
            CarProfile carProfile = carProfileDao.getById(id).get();
            List<CarImage> images = carImageDao.findCarImagesByCarProfileId(id);
            carProfile.setImages(images);
            return carProfile;
        }
        return null;
    }

    @Override
    public List<CarProfile> getAll() {
        List<CarProfile> carProfiles = carProfileDao.getAll();
        for (CarProfile carProfile : carProfiles) {
            carProfile.setImages(carImageDao.findCarImagesByCarProfileId(carProfile.getId()));
        }
        return carProfiles;
    }

    @Override
    public Long delete(CarProfile carProfile) {
        if (carProfile != null && carProfile.getId() > 0)
            return carProfileDao.delete(carProfile);
        return 0L;
    }

    @Override
    public Long update(CarProfile carProfile) {
        if (carProfile != null && carProfile.getId() > 0 && carProfile.getBodyType() != null && carProfile.getEngineType() != null
                && /*StringUtils.isNullOrEmpty(carProfile.getManufacturer()) && StringUtils.isNullOrEmpty(carProfile.getModel())
                &&*/ carProfile.getYearOfIssue() >= maxYearOfIssue)
            return carProfileDao.update(carProfile);
        return 0L;
    }

    @Override
    public Long save(CarProfile carProfile) {
        if (carProfile != null && carProfile.getId() > 0 && carProfile.getBodyType() != null && carProfile.getEngineType() != null
                && /*StringUtils.isNullOrEmpty(carProfile.getManufacturer()) && StringUtils.isNullOrEmpty(carProfile.getModel())
                &&*/ carProfile.getYearOfIssue() >= maxYearOfIssue)
            return carProfileDao.save(carProfile);
        return 0L;
    }

    private List<CarProfile> getCarProfilesWithMainImage(List<CarProfile> carProfiles) {
        List<Long> profilesWithoutImage = new ArrayList<>();
        carProfiles.forEach(carProfile -> {
            Optional<CarImage> mainCarProfileImage = carImageDao.getMainCarImageByCarProfileId(carProfile.getId());
            mainCarProfileImage.ifPresentOrElse(carProfile::setMainImage,
                    () -> {
                        LOGGER.debug("Car image with car profile id " + carProfile.getId() + " is not found");
                        profilesWithoutImage.add(carProfile.getId());
                    });
        });
        profilesWithoutImage.forEach(id -> carProfiles.remove(
                carProfiles.indexOf(carProfiles.stream().filter(carProfile -> carProfile.getId() != id).findFirst().get())));
        return carProfiles;
    }
}
