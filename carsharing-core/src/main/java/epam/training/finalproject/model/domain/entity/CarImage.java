package epam.training.finalproject.model.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "car_images")
public class CarImage extends AbstractEntity {

    @Column(name = "image_url", nullable = false)
    private String imageUrl;
    @Column(name = "is_main_image", nullable = false)
    private boolean mainImage;

    public boolean isMainImage() {
        return mainImage;
    }

    public void setMainImage(boolean mainImage) {
        this.mainImage = mainImage;
    }

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
