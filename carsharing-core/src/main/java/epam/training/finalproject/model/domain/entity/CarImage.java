package epam.training.finalproject.model.domain.entity;

public class CarImage extends AbstractEntity {

    private String imageUrl;
    private long carProfileId;

    public long getCarProfileId() {
        return carProfileId;
    }

    public void setCarProfileId(long carProfileId) {
        this.carProfileId = carProfileId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
