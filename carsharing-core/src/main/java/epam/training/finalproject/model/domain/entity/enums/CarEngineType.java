package epam.training.finalproject.model.domain.entity.enums;

public enum CarEngineType {
    FUEL("fuel"),
    ELECTRIC("electric");

    private String type;
    CarEngineType(String type){
        this.type = type;
    }
}
