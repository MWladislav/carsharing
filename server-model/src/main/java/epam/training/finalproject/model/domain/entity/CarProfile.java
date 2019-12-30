package epam.training.finalproject.model.domain.entity;

import epam.training.finalproject.model.domain.entity.enums.CarBodyType;
import epam.training.finalproject.model.domain.entity.enums.CarEngineType;

import java.util.List;

public class CarProfile extends AbstractEntity {

    private String manufacturer;
    private String model;
    private CarBodyType bodyType;
    private CarEngineType engineType;
    private int yearOfIssue;
    private List<CarImage> images;
    private List<Car> cars;
    private long popularity;

    public long getPopularity() {
        return popularity;
    }

    public long calcPopularity() {
        return cars.stream().mapToLong(car -> car.getOrders().size()).sum();
    }

    public void setPopularity(long popularity) {
        this.popularity = popularity;
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
