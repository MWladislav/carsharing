package epam.training.finalproject.carsharing.model.domain.entity;

import epam.training.finalproject.carsharing.model.domain.entity.enums.CarBodyType;
import epam.training.finalproject.carsharing.model.domain.entity.enums.CarEngineType;

import java.util.List;

public class Car extends Persistent {

    private String manufacturer;
    private String model;
    private CarBodyType bodyType;
    private CarEngineType engineType;
    private int yearOfIssue;
    private List<Order> orders;
    private List<CarImage> images;
    private boolean available;

    public Car() {}

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<CarImage> getImages() {
        return images;
    }

    public void setImages(List<CarImage> images) {
        this.images = images;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
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
