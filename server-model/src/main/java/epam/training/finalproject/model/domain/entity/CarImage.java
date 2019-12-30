package epam.training.finalproject.model.domain.entity;

public class CarImage extends AbstractEntity {

    private String imagePath;
    private long carProfileId;

    public long getCarProfileId() {
        return carProfileId;
    }

    public void setCarProfileId(long carProfileId) {
        this.carProfileId = carProfileId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
