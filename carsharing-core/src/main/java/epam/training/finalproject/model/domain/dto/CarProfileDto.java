package epam.training.finalproject.model.domain.dto;

import epam.training.finalproject.model.domain.entity.Car;
import epam.training.finalproject.model.domain.entity.CarImage;
import epam.training.finalproject.model.domain.entity.enums.CarBodyType;
import epam.training.finalproject.model.domain.entity.enums.CarEngineType;

import java.util.List;

public class CarProfileDto extends AbstractEntityDto {

    private String manufacturer;
    private String model;
    private CarBodyType bodyType;
    private CarEngineType engineType;
    private int yearOfIssue;
    private List<CarImage> images;
    private CarImage mainImage;
    private List<Car> cars;

    public CarProfileDto() {
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

    public List<CarImage> getImages() {
        return images;
    }

    public void setImages(List<CarImage> images) {
        this.images = images;
    }

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
}
