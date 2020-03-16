package epam.training.finalproject.model.service.impls;


import epam.training.finalproject.model.dao.interfaces.CarImageRepository;
import epam.training.finalproject.model.dao.interfaces.CarProfileDao;
import epam.training.finalproject.model.dao.interfaces.CarProfileRepository;
import epam.training.finalproject.model.dao.specification.CarImageSpecification;
import epam.training.finalproject.model.dao.specification.CarProfileSpecification;
import epam.training.finalproject.model.dao.specification.SearchCriteria;
import epam.training.finalproject.model.dao.specification.SpecificationBuilder;
import epam.training.finalproject.model.domain.dto.CarProfileDto;
import epam.training.finalproject.model.domain.entity.CarImage;
import epam.training.finalproject.model.domain.entity.CarProfile;
import epam.training.finalproject.model.service.conventer.EntityConventer;
import epam.training.finalproject.model.service.interfaces.CarProfileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarProfileServiceImpl implements CarProfileService {

    private static Logger LOGGER = Logger.getLogger(CarProfileServiceImpl.class);

    private final int maxYearOfIssue = 1990;
    private final CarImage defaultMainImage;
    private final CarProfileRepository cpRepo;
    private final CarImageRepository imageRepo;

    @Autowired
    private CarProfileDao carProfileDao;

    @Autowired
    public CarProfileServiceImpl(CarProfileRepository cpRepo, CarImageRepository imageRepo) {
        this.cpRepo = cpRepo;
        this.imageRepo = imageRepo;
        this.defaultMainImage = imageRepo.findOne(CarImageSpecification.findDefaultMainImage()).orElse(null);
    }

    @Transactional
    @Override
    public List<CarProfileDto> findCarProfilesByCriteria(List<SearchCriteria> criteria) {
        if (criteria != null && !criteria.isEmpty()) {
            Specification<CarProfile> criteriaSpec = new SpecificationBuilder<CarProfile>(criteria)
                    .build(CarProfileSpecification::new);
            List<CarProfile> carProfiles = getCarProfilesWithMainImage(cpRepo.findAll(criteriaSpec));
            return carProfiles.stream()
                    .map(carProfile -> {
//                        carProfile.setCars(null);
                        return (CarProfileDto)EntityConventer.convertToDto(carProfile);
                    })
                    .collect(Collectors.toList());
        }
        LOGGER.debug("Car model is null or empty!");
        return Collections.emptyList();
    }

    @Transactional
    @Override
    public CarProfileDto getById(Long id) {
        if (id > 0) {

            CarProfile carProfile = cpRepo.getOne(id);
            return (CarProfileDto) EntityConventer.convertToDto(carProfile);
//            CarProfile carProfile = carProfileDao.getById(id).get();
//            List<CarImage> images = carImageDao.findCarImagesByCarProfileId(id);
//            carProfile.setImages(images);
//            return carProfile;
        }
        return null;
    }

    @Transactional
    @Override
    public List<CarProfileDto> getAll() {
//         return getCarProfilesWithMainImage(repository.findAll());
        List<CarProfile> carProfiles = cpRepo.findAll();
        return carProfiles.stream().map(entity -> (CarProfileDto) EntityConventer.convertToDto(entity)).collect(Collectors.toList());
    }

    @Override
    public Long delete(CarProfileDto carProfileDto) {
        if (carProfileDto != null && carProfileDto.getId() > 0)
            return -1L;//carProfileDao.delete(carProfileDto);
        return 0L;
    }

    @Override
    public Long update(CarProfileDto carProfileDto) {
        if (carProfileDto != null && carProfileDto.getId() > 0 && carProfileDto.getBodyType() != null && carProfileDto.getEngineType() != null
                && /*StringUtils.isNullOrEmpty(carProfileDto.getManufacturer()) && StringUtils.isNullOrEmpty(carProfileDto.getModel())
                &&*/ carProfileDto.getYearOfIssue() >= maxYearOfIssue)
            return -1L;//carProfileDao.update(carProfileDto);
        return 0L;
    }

    @Override
    public Long save(CarProfileDto carProfileDto) {
        if (carProfileDto != null && carProfileDto.getId() > 0 && carProfileDto.getBodyType() != null && carProfileDto.getEngineType() != null
                && /*StringUtils.isNullOrEmpty(carProfileDto.getManufacturer()) && StringUtils.isNullOrEmpty(carProfileDto.getModel())
                &&*/ carProfileDto.getYearOfIssue() >= maxYearOfIssue)
            return -1L;//carProfileDao.save(carProfileDto);
        return 0L;
    }

    private List<CarProfile> getCarProfilesWithMainImage(List<CarProfile> carProfiles) {
//        List<Long> profilesWithoutImage = new ArrayList<>();
        carProfiles.forEach(carProfile -> {
            carProfile.getImages().stream()
                    .filter(CarImage::isMainImage)
                    .findFirst()
                    .ifPresentOrElse(carProfile::setMainImage,
                    () -> {
                        LOGGER.debug("Main image of car profile with id " + carProfile.getId() + " is not found");
                        carProfile.setMainImage(defaultMainImage);
//                        profilesWithoutImage.add(carProfile.getId());
                    });
        });
//        profilesWithoutImage.forEach(id -> carProfiles.remove(
//                carProfiles.indexOf(carProfiles.stream().filter(carProfile -> carProfile.getId() == id).findFirst().get())));
        return carProfiles;
    }
}
