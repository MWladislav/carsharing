package epam.training.finalproject.model.domain.dto;

public class CarImageDto extends AbstractEntityDto {

    private String imageUrl;
    private boolean mainImage;

    public CarImageDto() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isMainImage() {
        return mainImage;
    }

    public void setMainImage(boolean mainImage) {
        this.mainImage = mainImage;
    }
}
