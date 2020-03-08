package epam.training.finalproject.model.domain.entity.enums;

public enum CarBodyType {

    hatchback("hatchback"),
    sedan("sedan"),
    wagon("wagon"),
    van("van"),
    coupe("coupe");

    private String type;
    CarBodyType(String type){
        this.type = type;
    }
}
