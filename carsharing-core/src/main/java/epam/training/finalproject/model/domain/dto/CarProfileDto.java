package epam.training.finalproject.model.domain.dto;

import epam.training.finalproject.model.domain.entity.enums.CarBodyType;
import epam.training.finalproject.model.domain.entity.enums.CarEngineType;

import java.util.List;

public class CarProfileDto extends AbstractEntityDto {

    private String manufacturer;
    private String model;
    private CarBodyType bodyType;
    private CarEngineType engineType;
    private int yearOfIssue;
    private List<CarImageDto> imageDto;
    private CarImageDto mainImageDto;
    private List<CarDto> carDto;

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

    public List<CarImageDto> getImageDto() {
        return imageDto;
    }

    public void setImageDto(List<CarImageDto> imageDto) {
        this.imageDto = imageDto;
    }

    public CarImageDto getMainImageDto() {
        return mainImageDto;
    }

    public void setMainImageDto(CarImageDto mainImageDto) {
        this.mainImageDto = mainImageDto;
    }

    public List<CarDto> getCarDto() {
        return carDto;
    }

    public void setCarDto(List<CarDto> carDto) {
        this.carDto = carDto;
    }
}
