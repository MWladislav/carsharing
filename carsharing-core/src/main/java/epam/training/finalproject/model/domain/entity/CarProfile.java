package epam.training.finalproject.model.domain.entity;

import epam.training.finalproject.model.domain.entity.enums.CarBodyType;
import epam.training.finalproject.model.domain.entity.enums.CarEngineType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car_profiles")
public class CarProfile extends AbstractEntity {

    @Column(name = "manufacturer", length = 30, nullable = false)
    private String manufacturer;
    @Column(name = "model", length = 30, nullable = false)
    private String model;
    @Column(name = "body_type", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private CarBodyType bodyType;
    @Column(name = "engine_type", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private CarEngineType engineType;
    @Column(name = "year_of_issue", length = 30, nullable = false)
    private int yearOfIssue;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_profile_id")
    private List<CarImage> images;
    @Transient
    private CarImage mainImage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carProfile")
    private List<Car> cars;

    public CarImage getMainImage() {
        return mainImage;
    }

    public void setMainImage(CarImage mainImage) {
        this.mainImage = mainImage;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<CarImage> getImages() {
        return images;
    }

    public void setImages(List<CarImage> images) {
        this.setMainImage(images.stream().filter(CarImage::isMainImage).findFirst().orElse(null));
        images.remove(this.mainImage);
        this.images = images;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CarBodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(CarBodyType bodyType) {
        this.bodyType = bodyType;
    }

    public CarEngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(CarEngineType engineType) {
        this.engineType = engineType;
    }

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(int yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }
}
