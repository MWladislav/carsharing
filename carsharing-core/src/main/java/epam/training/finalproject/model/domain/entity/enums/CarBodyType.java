package epam.training.finalproject.model.domain.entity.enums;

public enum CarBodyType {

    HATCHBACK("hatchback"),
    SEDAN("sedan"),
    WAGON("wagon"),
    VAN("van"),
    COUPE("coupe");

    private String type;
    CarBodyType(String type){
        this.type = type;
    }
}
