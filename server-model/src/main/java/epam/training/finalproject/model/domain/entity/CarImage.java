package epam.training.finalproject.model.domain.entity;

public class CarImage extends Persistent{

    private String imagePath;
    private long carId;

    public CarImage(){}

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
